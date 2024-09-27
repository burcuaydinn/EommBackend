package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.request.CartItemRequest;
import com.ecommerce.ecommerce.dto.request.CartRequest;
import com.ecommerce.ecommerce.dto.response.CartItemResponse;
import com.ecommerce.ecommerce.dto.response.CartResponse;
import com.ecommerce.ecommerce.entity.Cart;
import com.ecommerce.ecommerce.entity.CartItem;
import com.ecommerce.ecommerce.entity.Product;
import com.ecommerce.ecommerce.entity.User;
import com.ecommerce.ecommerce.repository.CartRepository;
import com.ecommerce.ecommerce.repository.CartItemRepository;
import com.ecommerce.ecommerce.repository.ProductRepository;
import com.ecommerce.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private Long userId;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository, CartItemRepository cartItemRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    public CartResponse addItemToCart(CartItemRequest request) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));


        Cart cart = cartRepository.findByUserId(userId)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUser(user);
                    cartRepository.save(newCart);
                    return newCart;
                });


        Product product = productRepository.findById(request.productId())
                .orElseThrow(() -> new RuntimeException("Product not found"));


        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(request.quantity());
        cartItem.setCart(cart);

        cartItemRepository.save(cartItem);
        cart.getCartItems().add(cartItem);

        return new CartResponse(cart.getId(), user.getId(),
                cart.getCartItems().stream().map(item -> new CartItemResponse(
                        item.getProduct().getId(),
                        item.getProduct().getName(),
                        item.getQuantity(),
                        item.getProduct().getPrice()
                )).collect(Collectors.toList()));
    }

    @Override
    public CartResponse removeItemFromCart(Long userId, Long productId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));


        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));


        CartItem cartItem = cartItemRepository.findByCartIdAndProductId(cart.getId(), productId)
                .orElseThrow(() -> new RuntimeException("Product not found in cart"));

        cart.getCartItems().remove(cartItem);
        cartItemRepository.delete(cartItem);

        return new CartResponse(cart.getId(), user.getId(),
                cart.getCartItems().stream().map(item -> new CartItemResponse(
                        item.getProduct().getId(),
                        item.getProduct().getName(),
                        item.getQuantity(),
                        item.getProduct().getPrice()
                )).collect(Collectors.toList()));
    }

    @Override
    public CartResponse getCartByUserId(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));


        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        return new CartResponse(cart.getId(), user.getId(),
                cart.getCartItems().stream().map(item -> new CartItemResponse(  // CartItemResponse doğrudan kullanılıyor
                        item.getProduct().getId(),
                        item.getProduct().getName(),
                        item.getQuantity(),
                        item.getProduct().getPrice()
                )).collect(Collectors.toList()));

    }

    @Override
    public CartResponse addItemToCart(Long aLong, CartRequest cartRequest) {
        return null;
    }
}
