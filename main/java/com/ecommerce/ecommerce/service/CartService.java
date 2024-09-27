package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.request.CartItemRequest;
import com.ecommerce.ecommerce.dto.request.CartRequest;
import com.ecommerce.ecommerce.dto.response.CartResponse;

public interface CartService {


    CartResponse addItemToCart(CartItemRequest request);


    CartResponse removeItemFromCart(Long userId, Long productId);


    CartResponse getCartByUserId(Long userId);

    CartResponse addItemToCart(Long aLong, CartRequest cartRequest);

}
