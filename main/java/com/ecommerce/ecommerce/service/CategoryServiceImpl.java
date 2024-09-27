package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.request.CategoryRequest;
import com.ecommerce.ecommerce.dto.response.CategoryResponse;
import com.ecommerce.ecommerce.entity.Category;
import com.ecommerce.ecommerce.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional
    public CategoryResponse addCategory(CategoryRequest request) {
        Category category = new Category();
        category.setName(request.name());
        category.setType(request.type());

        categoryRepository.save(category);

        return new CategoryResponse(category.getId(), category.getName(), category.getType());
    }

    @Override
    public CategoryResponse updateCategory(Long categoryId, CategoryRequest request) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        category.setName(request.name());
        category.setType(request.type());

        categoryRepository.save(category);

        return new CategoryResponse(category.getId(), category.getName(), category.getType());
    }

    @Override
    public void deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        categoryRepository.delete(category);
    }

    @Override
    public List<CategoryResponse> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(category -> new CategoryResponse(
                        category.getId(),
                        category.getName(),
                        category.getType()))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryResponse getCategoryById(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        return new CategoryResponse(category.getId(), category.getName(), category.getType());
    }
}
