package com.ecommerce.ecommerce.dto.response;

import com.ecommerce.ecommerce.enums.OrderStatus;
import java.util.List;

public record OrderResponse(Long orderId, Long userId, OrderStatus orderStatus, List<OrderItemResponse> orderItems) {}
