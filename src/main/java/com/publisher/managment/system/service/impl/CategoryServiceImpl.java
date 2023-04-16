package com.publisher.managment.system.service.impl;

import com.publisher.managment.system.dto.CategoryDTO;
import com.publisher.managment.system.entity.Category;
import com.publisher.managment.system.exception.ExceptionMessage;
import com.publisher.managment.system.exception.ResourceNotFoundException;
import com.publisher.managment.system.mapper.CategoryMapper;
import com.publisher.managment.system.repository.CategoryRepository;
import com.publisher.managment.system.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl extends ExceptionMessage implements CategoryService {
    @Autowired private CategoryRepository categoryRepository;
    @Override
    @Transactional
    public CategoryDTO addCategory(CategoryDTO categoryDTO) {
        return CategoryMapper.toDto(categoryRepository.save(CategoryMapper.toEntity(categoryDTO)));
    }
    @Override
    public List<CategoryDTO> getCategories() {
        return categoryRepository.findAll().stream().map(CategoryMapper::toDto).collect(Collectors.toList());
    }
    @Override
    public CategoryDTO getCategoryById(Integer id) {
        return categoryRepository.findById(id).map(CategoryMapper::toDto).orElseThrow(()-> new ResourceNotFoundException(String.format(CATEGORY_NOT_FOUND, id)));
    }
    @Override
    @Transactional
    public CategoryDTO updateCategory(Integer id, CategoryDTO categoryDTO) {
        Category category = categoryRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(String.format(CATEGORY_NOT_FOUND, id)));
        return CategoryMapper.toDto(categoryRepository.save(CategoryMapper.toEntityForUpdate(category, categoryDTO)));
    }
    @Override
    @Transactional
    public void deleteCategoryById(Integer id) {
        Category category = categoryRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(String.format(CATEGORY_NOT_FOUND, id)));
        categoryRepository.delete(category);
    }
}
