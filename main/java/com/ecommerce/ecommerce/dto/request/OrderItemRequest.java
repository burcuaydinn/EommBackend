package com.ecommerce.ecommerce.dto.request;

public record OrderItemRequest(Long productId, Integer quantity, Double price) {}
