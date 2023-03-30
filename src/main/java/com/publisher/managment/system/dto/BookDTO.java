package com.publisher.managment.system.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDTO {
    private Integer id;
    @NotNull(message = "Book title is required")
    private String title;
    @NotNull(message = "Author is required")
    private String author;
    @NotNull(message = "Price is required")
    private Double price;
    private Integer quantity;
    @NotNull(message = "Assign a category")
    private CategoryDTO category;
}
