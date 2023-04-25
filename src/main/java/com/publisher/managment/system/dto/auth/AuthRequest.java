package com.publisher.managment.system.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {
    @NotEmpty(message = "Username is required")
    private String username;
    @NotEmpty(message = "Create a password")
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
