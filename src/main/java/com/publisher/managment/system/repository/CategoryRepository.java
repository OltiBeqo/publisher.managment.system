package com.publisher.managment.system.repository;

import com.publisher.managment.system.entity.Category;
import java.util.List;

public interface CategoryRepository {
    Category addCategory(Category category);
    List<Category> getCategories();
    Category getCategoryById(Integer id);
    Category updateCategory(Integer id);
    void deleteCategoryById(Integer id);
}
