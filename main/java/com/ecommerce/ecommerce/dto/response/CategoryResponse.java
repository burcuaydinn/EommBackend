package com.ecommerce.ecommerce.dto.response;

import com.ecommerce.ecommerce.enums.CategoryType;

public record CategoryResponse(Long id, String name, CategoryType type) {}
