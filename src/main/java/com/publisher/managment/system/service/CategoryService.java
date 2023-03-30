package com.publisher.managment.system.service;

import com.publisher.managment.system.dto.CategoryDTO;
import com.publisher.managment.system.entity.Category;

import java.util.List;

public interface CategoryService {
    CategoryDTO addCategory(CategoryDTO categoryDTO);
    List<CategoryDTO> getCategories();
    CategoryDTO getCategoryById(Integer id);
    CategoryDTO updateCategory(Integer id);
    void deleteCategoryById(Integer id);
}
