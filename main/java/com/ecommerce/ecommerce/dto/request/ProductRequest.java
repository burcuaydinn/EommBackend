package com.ecommerce.ecommerce.dto.request;



import com.ecommerce.ecommerce.enums.CategoryType;

public record ProductRequest(String name, String description, Double price, Integer stockQuantity, CategoryType category) {}
