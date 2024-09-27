package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.request.ProductRequest;
import com.ecommerce.ecommerce.dto.response.ProductResponse;
import com.ecommerce.ecommerce.dto.response.UserResponse;
import com.ecommerce.ecommerce.service.ProductService;
import com.ecommerce.ecommerce.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/admin")
public class AdminController {

    private final ProductService productService;
    private final UserService userService;

    public AdminController(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }


    @PostMapping("/products")
    public ResponseEntity<ProductResponse> addProduct(@RequestBody ProductRequest request, Principal principal) {
        System.out.println("Authenticated user: " + principal.getName());
        ProductResponse response = productService.addProduct(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }



    @PutMapping("/products/{productId}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long productId, @RequestBody ProductRequest request) {
        ProductResponse response = productService.updateProduct(productId, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @DeleteMapping("/products/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PostMapping("/users")
    public ResponseEntity<UserResponse> addUser(@RequestBody UserResponse userResponse) {
        UserResponse response = userService.updateUser(null, userResponse);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PutMapping("/users/{userId}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long userId, @RequestBody UserResponse userResponse) {
        UserResponse response = userService.updateUser(userId, userResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
