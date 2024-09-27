package com.ecommerce.ecommerce.repository;

import com.ecommerce.ecommerce.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {


    List<Order> findByUserId(Long userId);


    List<Order> findByOrderStatus(String orderStatus);


    @Query("SELECT o FROM Order o WHERE o.user.id = :userId AND o.orderDate > :date")
    List<Order> findUserOrdersAfterDate(Long userId, LocalDate date);
}
