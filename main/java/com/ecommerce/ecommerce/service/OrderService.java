package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.request.OrderRequest;
import com.ecommerce.ecommerce.dto.response.OrderResponse;
import java.util.List;

public interface OrderService {


    OrderResponse createOrder(OrderRequest request);


    OrderResponse updateOrderStatus(Long orderId, String status);


    List<OrderResponse> getAllOrders();


    OrderResponse getOrderById(Long orderId);
}
