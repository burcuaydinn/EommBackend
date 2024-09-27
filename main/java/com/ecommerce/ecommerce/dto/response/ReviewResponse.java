package com.ecommerce.ecommerce.dto.response;

public record ReviewResponse(Long id, Long productId, Long userId, Integer rating, String comment) {}
