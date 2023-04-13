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
    @NotEmpty(message = "Book title is required")
    private String title;
    @NotEmpty(message = "Author is required")
    private String author;
    @NotEmpty(message = "Price is required")
    private Double price;
    @NotNull(message = "Cannot be null")@NotEmpty(message = "Cannot be empty")
    private Integer quantity;
    @NotEmpty(message = "Assign a category")
    private CategoryDTO category;
}
