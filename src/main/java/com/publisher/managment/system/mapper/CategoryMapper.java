package com.publisher.managment.system.mapper;

import com.publisher.managment.system.dto.CategoryDTO;
import com.publisher.managment.system.entity.Category;

public class CategoryMapper {
    public static CategoryDTO toDto(Category category){
        return CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    public static Category toEntity(CategoryDTO categoryDTO){
        return Category.builder()
                .id(categoryDTO.getId())
                .name(categoryDTO.getName())
                .build();
    }
    public static Category toEntityForUpdate(CategoryDTO categoryDTO, Category category){
        category.setName(categoryDTO.getName());
        return category;
    }
}
