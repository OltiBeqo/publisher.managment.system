package com.publisher.managment.system.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class AuthRequest {
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotEmpty(message = "Name is required")
    private String firstname;
    @NotEmpty(message = "Lastname is required")
    private String lastname;
    @NotEmpty(message = "Define gender")
    private String gender;
    @NotEmpty(message = "Select role")
    private String role;

}
