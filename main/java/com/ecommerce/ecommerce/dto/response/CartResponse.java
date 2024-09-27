package com.ecommerce.ecommerce.dto.response;

import java.util.List;

public record CartResponse(Long id, Long userId, List<CartItemResponse> cartItems) {}
