package com.publisher.managment.system.controller;

import com.publisher.managment.system.dto.UserDTO;
import com.publisher.managment.system.dto.auth.AuthRequest;
import com.publisher.managment.system.dto.auth.TokenDTO;
import com.publisher.managment.system.entity.User;
import com.publisher.managment.system.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.time.Instant;
import java.util.Arrays;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor @Validated
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtEncoder jwtEncoder;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody @Valid AuthRequest request) {
        try {
            Authentication authentication =
                    authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

            User user = (User) authentication.getPrincipal();

            Instant now = Instant.now();

            String scope =
                    authentication.getAuthorities().stream()
                            .map(GrantedAuthority::getAuthority)
                            .collect(Collectors.joining(" "));

            JwtClaimsSet claims =
                    JwtClaimsSet.builder()
                            .issuer("ikubinfo.al")
                            .issuedAt(now)
                            .subject(String.format("%s,%s", user.getId(), user.getUsername()))
                            .claim("roles", scope)
                            .build();

            String token = this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION, "Bearer ".concat(token))
                    .body(new TokenDTO("Bearer ".concat(token)));
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody @Valid UserDTO u){
        return ResponseEntity.ok(userService.registerUser(u));
    }

}