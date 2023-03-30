package com.publisher.managment.system.repository.impl;

import com.publisher.managment.system.entity.Category;
import com.publisher.managment.system.repository.CategoryRepository;
import com.publisher.managment.system.repository.impl.queries.NamedQueries;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@Repository
public class CategoryRepositoryImpl extends NamedQueries implements CategoryRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public Category addCategory(Category category) {
        entityManager.persist(category);
        return category;
    }

    @Override
    public List<Category> getCategories() {
        return entityManager.createNamedQuery(GET_CATEGORIES, Category.class).getResultList();
    }

    @Override
    public Category getCategoryById(Integer id) {
        return entityManager.createNamedQuery(GET_CATEGORIES_BY_ID, Category.class).setParameter("id", id).getSingleResult();
    }

    @Override
    public Category updateCategory(Integer id) {
        Category category = getCategoryById(id);
        return entityManager.merge(category);
    }

    @Override
    public void deleteCategoryById(Integer id) {
        Category category = getCategoryById(id);
        entityManager.remove(category);
    }
}
