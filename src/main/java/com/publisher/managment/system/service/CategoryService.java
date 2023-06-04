package com.publisher.managment.system.service;

import com.publisher.managment.system.dto.CategoryDTO;
import com.publisher.managment.system.dto.request.SearchRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {
    CategoryDTO addCategory(CategoryDTO categoryDTO);

    List<CategoryDTO> getCategories();

    CategoryDTO getCategoryById(Integer id);

    CategoryDTO updateCategory(CategoryDTO categoryDTO);

    void deleteCategoryById(Integer id);

    Page<CategoryDTO> getCategoriesPaginated(Pageable pageable);

    Page<CategoryDTO> searchCategory(SearchRequest request);
}
