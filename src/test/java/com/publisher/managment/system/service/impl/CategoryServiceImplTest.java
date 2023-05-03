package com.publisher.managment.system.service.impl;

import com.publisher.managment.system.entity.Category;
import com.publisher.managment.system.exception.ResourceNotFoundException;
import com.publisher.managment.system.repository.CategoryRepository;
import com.publisher.managment.system.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CategoryServiceImplTest {

    @MockBean
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryService toTest;

    @Test
    public void test_addCategory_ok() {
        Category category = new Category();
        category.setId(1);
        category.setName("category");
        category.setCreatedAt(LocalDate.of(2023, 1, 1).atStartOfDay());
        category.setModifiedAt(LocalDate.of(2023, 1, 1).atStartOfDay());
        when(categoryRepository.save(Mockito.<Category>any())).thenReturn(category);

    }

    @Test
    public void test_getCategories_ok() {
        Category category = new Category();
        category.setId(1);
        category.setName("category");
        category.setCreatedAt(LocalDate.of(2023, 1, 1).atStartOfDay());
        category.setModifiedAt(LocalDate.of(2023, 1, 1).atStartOfDay());

        ArrayList<Category> categoryList = new ArrayList<>();
        categoryList.add(category);
        when(categoryRepository.findAll()).thenReturn(categoryList);
        assertEquals(1, categoryList.size());
    }

    @Test
    public void test_getCategoryById_ok() {
        Category category = new Category();
        category.setId(1);
        category.setName("category");
        category.setCreatedAt(LocalDate.of(2023, 1, 1).atStartOfDay());
        category.setModifiedAt(LocalDate.of(2023, 1, 1).atStartOfDay());

        Optional<Category> result = Optional.of(category);
        when(categoryRepository.findById(Mockito.<Integer>any())).thenReturn(result);
        assertEquals(1, category.getId().intValue());
    }

    @Test
    public void test_getCategoryById_ko() {
        when(categoryRepository.findAll()).thenThrow(new ResourceNotFoundException("not found"));
        assertThrows(ResourceNotFoundException.class, () -> toTest.getCategories());
    }

    public void test_updateCategory_ok() {
        //TODO
    }

    @Test
    public void test_deleteCategoryById_ok() {
        doNothing().when(categoryRepository).delete(Mockito.<Category>any());
        when(categoryRepository.findById(Mockito.<Integer>any())).thenReturn(Optional.of(new Category()));
        toTest.deleteCategoryById(1);
    }

    @Test
    public void test_deleteCategoryById_ko() {
        doThrow(new ResourceNotFoundException("not found")).when(categoryRepository).delete(Mockito.<Category>any());
        when(categoryRepository.findById(Mockito.<Integer>any())).thenReturn(Optional.of(new Category()));
        assertThrows(ResourceNotFoundException.class, () -> toTest.deleteCategoryById(1));
    }
}
