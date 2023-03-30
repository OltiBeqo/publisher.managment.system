package com.publisher.managment.system.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UserDTO {
    private Integer id;
    @NotNull(message = "Name is required")
    private String firstname;
    @NotNull(message = "Lastname is required")
    private String lastname;
    @NotNull(message = "Username is required")
    private String username;
    @NotNull(message = "Password is required")
    private String password;
    private String role;
    private LocalDate createdAt;
}
