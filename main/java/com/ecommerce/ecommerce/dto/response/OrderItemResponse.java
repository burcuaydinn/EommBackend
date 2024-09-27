package com.ecommerce.ecommerce.dto.response;

public record OrderItemResponse(Long productId, String productName, Integer quantity, Double price) {}
