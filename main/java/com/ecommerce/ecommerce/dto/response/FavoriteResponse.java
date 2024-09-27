package com.ecommerce.ecommerce.dto.response;

public record FavoriteResponse(Long id, Long userId, Long productId, String productName, Double productPrice) {}
