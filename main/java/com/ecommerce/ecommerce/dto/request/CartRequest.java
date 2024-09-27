package com.ecommerce.ecommerce.dto.request;

public record CartRequest(Long userId, Long productId, Integer quantity) {}

