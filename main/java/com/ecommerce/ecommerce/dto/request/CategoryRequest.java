package com.ecommerce.ecommerce.dto.request;

import com.ecommerce.ecommerce.enums.CategoryType;

public record CategoryRequest(String name, CategoryType type) {}
