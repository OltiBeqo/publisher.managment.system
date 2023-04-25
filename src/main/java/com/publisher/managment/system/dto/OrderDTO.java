package com.publisher.managment.system.dto;

import com.publisher.managment.system.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDTO {
    private Integer id;
    private String comment;
    private double discount;
    private UserDTO user;
    private Double totalAmount;
    private String orderStatus;
    @NotNull(message = "at least a books is required to create an order")
    private List<BookDTO> books;
    @NotNull(message = "Library is required")
    private LibraryDTO library;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private UserDTO courier;
    private boolean deleted;
}
