package com.publisher.managment.system.controller;


import com.publisher.managment.system.BaseTest;
import com.publisher.managment.system.dto.UserDTO;
import com.publisher.managment.system.dto.auth.AuthRequest;
import com.publisher.managment.system.entity.User;
import com.publisher.managment.system.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class AuthControllerTest extends BaseTest {

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private UserService toTest;

    @MockBean
    private JwtEncoder jwtEncoder;

    @Test
    public void test_login_ok() throws Exception {
        Authentication auth = Mockito.mock(UsernamePasswordAuthenticationToken.class);
        Mockito.doReturn(auth).when(authenticationManager).authenticate(Mockito.any());
        User fakeUser = new User();
        fakeUser.setUsername("username");
        Mockito.doReturn(fakeUser).when(auth).getPrincipal();
        Mockito.doReturn(Arrays.asList(new SimpleGrantedAuthority("test")))
                .when(auth).getAuthorities();

        Jwt fakeJwt = Mockito.mock(Jwt.class);
        Mockito.doReturn(fakeJwt).when(jwtEncoder).encode(Mockito.any());
        Mockito.doReturn("Bearer ").when(fakeJwt).getTokenValue();

        mvc.perform(MockMvcRequestBuilders.post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(new AuthRequest("username", "password", " ", " ", " ", " "))))
                .andExpect(status().isOk());
    }

    @Test
    public void test_registerUser_ko() throws Exception {
        SecurityContextHolder.getContext().setAuthentication(getAuthentication("ROLE_COURIER"));
        doReturn(new UserDTO()).when(toTest).registerUser(any());
        mvc.perform(MockMvcRequestBuilders.post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(new UserDTO())))
                .andExpect(status().is4xxClientError());
    }
}
