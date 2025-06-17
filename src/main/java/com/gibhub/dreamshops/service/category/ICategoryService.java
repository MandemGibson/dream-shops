package com.gibhub.dreamshops.service.category;

import java.util.List;

import com.gibhub.dreamshops.models.Category;
import com.gibhub.dreamshops.request.UpdateCategoryRequest;

public interface ICategoryService {
    Category addCategory(Category category);

    Category getCategoryById(Long id);

    Category getCategoryByName(String name);

    List<Category> getAllCategories();

    Category updateCategory(UpdateCategoryRequest category, Long id);

    void deleteCategoryById(Long id);

}
