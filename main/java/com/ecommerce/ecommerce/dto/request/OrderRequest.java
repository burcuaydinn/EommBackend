package com.ecommerce.ecommerce.dto.request;

import com.ecommerce.ecommerce.enums.OrderStatus;
import java.util.List;

public record OrderRequest(Long userId, List<OrderItemRequest> orderItems, OrderStatus orderStatus) {}
