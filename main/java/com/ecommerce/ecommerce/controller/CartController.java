package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.request.CartRequest;
import com.ecommerce.ecommerce.dto.response.CartResponse;
import com.ecommerce.ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")

public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }


    @PostMapping("/add")
    public CartResponse addToCart(@RequestBody CartRequest cartRequest) {
        return cartService.addItemToCart(cartRequest.userId(), cartRequest);
    }


    @DeleteMapping("/remove/{productId}")
    public CartResponse removeFromCart(@RequestParam Long userId, @PathVariable Long productId) {
        return cartService.removeItemFromCart(userId, productId);
    }


    @GetMapping("/view")
    public CartResponse viewCart(@RequestParam Long userId) {
        return cartService.getCartByUserId(userId);
    }
}
