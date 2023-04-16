package com.publisher.managment.system.dto;

import com.publisher.managment.system.entity.enums.Gender;
import com.publisher.managment.system.entity.enums.Role;
import lombok.*;
import org.hibernate.engine.spi.ManagedEntity;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UserDTO {
    private Integer id;
    @NotEmpty(message = "Name is required")
    private String firstname;
    @NotEmpty(message = "Lastname is required")
    private String lastname;
    @NotEmpty(message = "Username is required")
    private String username;
    @NotEmpty(message = "Password is required")
    private String password;
    @NotEmpty(message = "Define gender")
    private String gender;
    @NotEmpty(message = "Select role")
    private String role;
    @CreatedDate
    private LocalDateTime createdAt;

}
