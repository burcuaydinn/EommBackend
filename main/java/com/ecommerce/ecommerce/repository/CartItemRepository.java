package com.ecommerce.ecommerce.repository;

import com.ecommerce.ecommerce.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {


    List<CartItem> findByCartId(Long cartId);


    Optional<CartItem> findByCartIdAndProductId(Long cartId, Long productId);
}
