package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.request.CartItemRequest;
import com.ecommerce.ecommerce.dto.response.CartResponse;
import com.ecommerce.ecommerce.service.CartService;
import com.ecommerce.ecommerce.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CartService cartService;

    @Mock
    private ProductService productService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void testGetAllProducts() throws Exception {
        when(productService.getAllProducts()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/user/products")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    void testAddToCart() throws Exception {

        CartResponse cartResponse = new CartResponse(1L, 1L, Collections.emptyList());

        when(cartService.addItemToCart(any(CartItemRequest.class))).thenReturn(cartResponse);

        mockMvc.perform(post("/user/cart/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"userId\": 1, \"productId\": 2, \"quantity\": 1 }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

}