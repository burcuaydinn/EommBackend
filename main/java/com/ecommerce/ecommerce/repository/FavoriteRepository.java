package com.ecommerce.ecommerce.repository;

import com.ecommerce.ecommerce.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {


    List<Favorite> findByUserId(Long userId);


    Optional<Favorite> findByUserIdAndProductId(Long userId, Long productId);
}


