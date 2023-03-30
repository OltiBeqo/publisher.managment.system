package com.publisher.managment.system.dto;

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
    private Double totalAmount;
    @NotNull(message = "at least a book is required to create an order")
    private List<BookDTO> book;
    private LibraryDTO library;
    private LocalDate createdAt;
}
