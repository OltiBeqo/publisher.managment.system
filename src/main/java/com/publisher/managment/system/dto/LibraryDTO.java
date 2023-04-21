package com.publisher.managment.system.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LibraryDTO {

    private Integer id;
    @NotEmpty(message = "Library name is required")
    private String library;
    @NotEmpty(message = "address is required")
    private String address;
    @NotEmpty(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;
    @NotEmpty(message = "Phone number is required")
    private String phoneNumber;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private boolean deleted;
}
