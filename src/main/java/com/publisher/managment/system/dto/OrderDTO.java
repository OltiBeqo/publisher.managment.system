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
    private User user;
    private Double totalAmount;
    private String orderStatus;
    private String payment;
    @NotNull(message = "at least a books is required to create an order")
    private List<BookDTO> books;
    private LibraryDTO library;
    private LocalDateTime createdAt;
    private UserDTO courier;
}
