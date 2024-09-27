package com.ecommerce.ecommerce.repository;

import com.ecommerce.ecommerce.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {


    List<OrderItem> findByOrderId(Long orderId);
}
