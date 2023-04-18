package com.publisher.managment.system.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDTO {
    private Integer id;
    @NotEmpty(message = "Category name is required")
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
