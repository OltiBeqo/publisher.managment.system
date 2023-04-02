package com.publisher.managment.system.service.impl;

import com.publisher.managment.system.dto.CategoryDTO;
import com.publisher.managment.system.repository.CategoryRepository;
import com.publisher.managment.system.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public CategoryDTO addCategory(CategoryDTO categoryDTO) {
        return null;
    }

    @Override
    public List<CategoryDTO> getCategories() {
        return null;
    }

    @Override
    public CategoryDTO getCategoryById(Integer id) {
        return null;
    }

    @Override
    public CategoryDTO updateCategory(Integer id) {
        return null;
    }

    @Override
    public void deleteCategoryById(Integer id) {

    }
}
