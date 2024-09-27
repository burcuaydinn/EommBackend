package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.request.*;
import com.ecommerce.ecommerce.dto.response.*;
import com.ecommerce.ecommerce.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/user")
public class UserController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final CartService cartService;
    private final FavoriteService favoriteService;
    private final OrderService orderService;
    private final ReviewService reviewService;
    private final AddressService addressService;
    private final PaymentService paymentService;
    private Long userId;

    @Autowired
    public UserController(ProductService productService, CategoryService categoryService,
                          CartService cartService, FavoriteService favoriteService,
                          OrderService orderService, ReviewService reviewService,
                          AddressService addressService, PaymentService paymentService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.cartService = cartService;
        this.favoriteService = favoriteService;
        this.orderService = orderService;
        this.reviewService = reviewService;
        this.addressService = addressService;
        this.paymentService = paymentService;
    }

    @GetMapping("/products")
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }


    @GetMapping("/categories")
    public List<CategoryResponse> getAllCategories() {
        return categoryService.getAllCategories();
    }


    @PostMapping("/cart/add")
    public CartResponse addToCart(@RequestBody CartItemRequest cartItemRequest) {
        return cartService.addItemToCart(cartItemRequest);
    }


    @DeleteMapping("/cart/remove")
    public CartResponse removeFromCart(@RequestParam Long userId, @RequestParam Long productId) {
        return cartService.removeItemFromCart(userId, productId);
    }


    @GetMapping("/cart")
    public CartResponse getCart(@RequestParam Long userId) {
        return cartService.getCartByUserId(userId);
    }


    @PostMapping("/favorite/add")
    public FavoriteResponse addToFavorites(@RequestBody FavoriteRequest favoriteRequest) {
        return favoriteService.addFavorite(favoriteRequest.userId(), favoriteRequest.productId());
    }



    @DeleteMapping("/favorite/remove")
    public void removeFromFavorites(@RequestParam Long userId, @RequestParam Long productId) {
        favoriteService.removeFavorite(userId, productId);
    }



    @GetMapping("/favorites")
    public List<FavoriteResponse> getFavorites(@RequestParam Long userId) {
        return favoriteService.getUserFavorites(userId);
    }


    @PostMapping("/order")
    public OrderResponse createOrder(@RequestBody OrderRequest orderRequest) {
        return orderService.createOrder(orderRequest);
    }


    @PostMapping("/review/add")
    public ReviewResponse addReview(@RequestParam Long productId, @RequestParam Long userId,
                                    @RequestBody ReviewRequest reviewRequest) {
        return reviewService.addReview(productId, userId, reviewRequest);
    }


    @GetMapping("/reviews/{productId}")
    public List<ReviewResponse> getProductReviews(@PathVariable Long productId) {
        return reviewService.getReviewsByProductId(productId);
    }


    @PostMapping("/address/add")
    public AddressResponse addAddress(@RequestBody AddressRequest addressRequest) {
        return addressService.addAddress(userId, addressRequest);
    }



    @PostMapping("/payment")
    public PaymentResponse processPayment(@RequestParam Long orderId, @RequestBody PaymentRequest paymentRequest) {
        return paymentService.processPayment(orderId, paymentRequest);
    }

}
