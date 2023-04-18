package com.publisher.managment.system.mapper;

import com.publisher.managment.system.dto.CategoryDTO;
import com.publisher.managment.system.entity.Category;

import java.time.LocalDateTime;

public class CategoryMapper {
    public static CategoryDTO toDto(Category category){
        return CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Category toEntity(CategoryDTO categoryDTO){
        return Category.builder()
                .id(categoryDTO.getId())
                .name(categoryDTO.getName())
                .createdAt(LocalDateTime.now())
                .build();
    }
    public static Category toEntityForUpdate(Category category, CategoryDTO categoryDTO){
        category.setName(categoryDTO.getName());
        category.setModifiedAt(LocalDateTime.now());
        return category;
    }
}
