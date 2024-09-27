package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.request.ProductRequest;
import com.ecommerce.ecommerce.dto.response.ProductResponse;
import com.ecommerce.ecommerce.enums.CategoryType;
import com.ecommerce.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/add")
    public ProductResponse addProduct(@RequestBody ProductRequest productRequest) {

        String name = productRequest.name();
        String description = productRequest.description();
        Double price = productRequest.price();
        Integer stockQuantity = productRequest.stockQuantity();
        CategoryType category = productRequest.category();


        ProductRequest newProductRequest = new ProductRequest(name, description, price, stockQuantity, category);
        return productService.addProduct(newProductRequest);
    }
}
