package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.request.CategoryRequest;
import com.ecommerce.ecommerce.dto.response.CategoryResponse;

import java.util.List;

public interface CategoryService {


    CategoryResponse addCategory(CategoryRequest request);


    CategoryResponse updateCategory(Long categoryId, CategoryRequest request);


    void deleteCategory(Long categoryId);


    List<CategoryResponse> getAllCategories();


    CategoryResponse getCategoryById(Long categoryId);
}
