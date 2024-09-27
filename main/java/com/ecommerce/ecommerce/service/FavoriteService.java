package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.response.FavoriteResponse;

import java.util.List;

public interface FavoriteService {


    FavoriteResponse addFavorite(Long userId, Long productId);


    void removeFavorite(Long userId, Long productId);


    List<FavoriteResponse> getUserFavorites(Long userId);
}
