package com.ecommerce.ecommerce.dto.response;

import com.ecommerce.ecommerce.enums.CategoryType;

public record ProductResponse(Long id, String name, String description, Double price, Integer stockQuantity, CategoryType category) {}
