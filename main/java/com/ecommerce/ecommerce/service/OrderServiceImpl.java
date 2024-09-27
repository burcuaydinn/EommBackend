package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.request.OrderRequest;
import com.ecommerce.ecommerce.dto.response.OrderResponse;
import com.ecommerce.ecommerce.dto.response.OrderItemResponse;
import com.ecommerce.ecommerce.entity.Order;
import com.ecommerce.ecommerce.entity.OrderItem;
import com.ecommerce.ecommerce.entity.Product;
import com.ecommerce.ecommerce.entity.User;
import com.ecommerce.ecommerce.repository.OrderRepository;
import com.ecommerce.ecommerce.repository.ProductRepository;
import com.ecommerce.ecommerce.repository.UserRepository;
import com.ecommerce.ecommerce.repository.OrderItemRepository;
import com.ecommerce.ecommerce.enums.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository, OrderItemRepository orderItemRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.orderItemRepository = orderItemRepository;
        this.productRepository = productRepository;
    }

    @Override
    public OrderResponse createOrder(OrderRequest request) {

        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));


        Order order = new Order();
        order.setUser(user);
        order.setOrderStatus(request.orderStatus());

        orderRepository.save(order);


        List<OrderItem> orderItems = request.orderItems().stream()
                .map(item -> {
                    Product product = productRepository.findById(item.productId())
                            .orElseThrow(() -> new RuntimeException("Product not found"));
                    OrderItem orderItem = new OrderItem();
                    orderItem.setProduct(product); // Product bilgisi ilişkilendiriliyor
                    orderItem.setQuantity(item.quantity());
                    orderItem.setOrder(order);
                    return orderItem;
                })
                .collect(Collectors.toList());


        orderItemRepository.saveAll(orderItems);
        order.setOrderItems(orderItems);


        return new OrderResponse(order.getId(), user.getId(), order.getOrderStatus(),
                orderItems.stream().map(item -> new OrderItemResponse(
                        item.getProduct().getId(), // Ürün ID
                        item.getProduct().getName(), // Ürün adı
                        item.getQuantity(), // Miktar
                        item.getProduct().getPrice() // Ürün fiyatı
                )).collect(Collectors.toList()));
    }

    @Override
    public OrderResponse updateOrderStatus(Long orderId, String status) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setOrderStatus(OrderStatus.valueOf(status.toUpperCase()));
        orderRepository.save(order);

        return new OrderResponse(order.getId(), order.getUser().getId(), order.getOrderStatus(),
                order.getOrderItems().stream().map(item -> new OrderItemResponse(
                        item.getProduct().getId(),
                        item.getProduct().getName(),
                        item.getQuantity(),
                        item.getProduct().getPrice()
                )).collect(Collectors.toList()));
    }

    @Override
    public List<OrderResponse> getAllOrders() {

        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(order -> new OrderResponse(order.getId(), order.getUser().getId(), order.getOrderStatus(),
                        order.getOrderItems().stream().map(item -> new OrderItemResponse(
                                item.getProduct().getId(),
                                item.getProduct().getName(),
                                item.getQuantity(),
                                item.getProduct().getPrice()
                        )).collect(Collectors.toList())))
                .collect(Collectors.toList());
    }

    @Override
    public OrderResponse getOrderById(Long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        return new OrderResponse(order.getId(), order.getUser().getId(), order.getOrderStatus(),
                order.getOrderItems().stream().map(item -> new OrderItemResponse(
                        item.getProduct().getId(),
                        item.getProduct().getName(),
                        item.getQuantity(),
                        item.getProduct().getPrice()
                )).collect(Collectors.toList()));
    }
}
