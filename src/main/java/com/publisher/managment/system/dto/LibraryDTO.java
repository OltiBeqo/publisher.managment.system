package com.publisher.managment.system.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LibraryDTO {

    private Integer id;
    @NotNull(message = "Library name is required")
    private String library;
    @NotNull(message = "address is required")
    private String address;
    @Email(message = "Email is not valid")
    @NotNull(message = "Email is required")
    private String email;
    @NotNull(message = "Phone number is required")
    private String phoneNumber;
    private List<OrderDTO> orders;
}
