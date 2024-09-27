package com.ecommerce.ecommerce.dto.response;

public record CartItemResponse(Long productId, String productName, Integer quantity, Double price) {}
