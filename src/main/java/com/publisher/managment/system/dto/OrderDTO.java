package com.publisher.managment.system.dto;

import com.publisher.managment.system.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
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
    @NotNull(message = "at least a book is required to create an order")
    private List<BookDTO> book;
    private LibraryDTO library;
    private LocalDate createdAt;
    private UserDTO courier;
}
