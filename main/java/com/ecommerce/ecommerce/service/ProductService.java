package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.request.ProductRequest;
import com.ecommerce.ecommerce.dto.response.ProductResponse;
import java.util.List;

public interface ProductService {


    ProductResponse addProduct(ProductRequest request);


    ProductResponse updateProduct(Long productId, ProductRequest request);


    void deleteProduct(Long productId);


    List<ProductResponse> getAllProducts();


    ProductResponse getProductById(Long productId);
}
