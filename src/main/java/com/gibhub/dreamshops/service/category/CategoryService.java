package com.gibhub.dreamshops.service.category;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gibhub.dreamshops.exceptions.AlreadyExistsException;
import com.gibhub.dreamshops.exceptions.ResourceNotFoundException;
import com.gibhub.dreamshops.models.Category;
import com.gibhub.dreamshops.repository.CategoryRepository;
import com.gibhub.dreamshops.request.UpdateCategoryRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class CategoryService implements ICategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public Category addCategory(Category category) {
        return Optional.of(category).filter(c -> !categoryRepository.existsByName(c.getName()))
                .map(categoryRepository::save)
                .orElseThrow(() -> new AlreadyExistsException("Category already exists"));

    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category updateCategory(UpdateCategoryRequest category, Long id) {
        return categoryRepository.findById(id)
                .map(existinCategory -> updateExistingCategory(category, existinCategory))
                .map(categoryRepository::save)
                .orElseThrow(() -> new ResourceNotFoundException("null"));
    }

    private Category updateExistingCategory(UpdateCategoryRequest request, Category existinCategory) {
        existinCategory.setName(request.getName());

        return existinCategory;
    }

    @Override
    public void deleteCategoryById(Long id) {
        categoryRepository.findById(id)
                .ifPresentOrElse(categoryRepository::delete, () -> {
                    throw new ResourceNotFoundException("Category not found");
                });
    }

}
