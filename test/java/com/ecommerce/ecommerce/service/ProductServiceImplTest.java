package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.request.ProductRequest;
import com.ecommerce.ecommerce.dto.response.ProductResponse;
import com.ecommerce.ecommerce.entity.Product;
import com.ecommerce.ecommerce.enums.CategoryType;
import com.ecommerce.ecommerce.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setDescription("Test Description");
        product.setPrice(100.0);
        product.setStockQuantity(10);
        product.setCategory(CategoryType.ELECTRONICS);
    }

    @Test
    public void testAddProduct() {

        when(productRepository.save(any(Product.class))).thenReturn(product);


        ProductRequest productRequest = new ProductRequest("Test Product", "Test Description", 100.0, 10, CategoryType.ELECTRONICS);


        ProductResponse productResponse = productService.addProduct(productRequest);


        assertNotNull(productResponse);
        assertEquals("Test Product", productResponse.name());
        assertEquals("Test Description", productResponse.description());
        assertEquals(100.0, productResponse.price());
        assertEquals(10, productResponse.stockQuantity());
        assertEquals(CategoryType.ELECTRONICS, productResponse.category());

        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    public void testUpdateProduct() {

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenReturn(product);


        ProductRequest productRequest = new ProductRequest("Updated Product", "Updated Description", 200.0, 20, CategoryType.BOOKS);


        ProductResponse productResponse = productService.updateProduct(1L, productRequest);


        assertNotNull(productResponse);
        assertEquals("Updated Product", productResponse.name());
        assertEquals("Updated Description", productResponse.description());
        assertEquals(200.0, productResponse.price());
        assertEquals(20, productResponse.stockQuantity());
        assertEquals(CategoryType.BOOKS, productResponse.category());


        verify(productRepository, times(1)).findById(1L);
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    public void testDeleteProduct() {

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));


        productService.deleteProduct(1L);


        verify(productRepository, times(1)).findById(1L);
        verify(productRepository, times(1)).delete(any(Product.class));
    }

    @Test
    public void testGetAllProducts() {

        List<Product> products = Arrays.asList(product, new Product());
        when(productRepository.findAll()).thenReturn(products);


        List<ProductResponse> productResponses = productService.getAllProducts();


        assertNotNull(productResponses);
        assertEquals(2, productResponses.size());


        verify(productRepository, times(1)).findAll();
    }

    @Test
    public void testGetProductById() {

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));


        ProductResponse productResponse = productService.getProductById(1L);


        assertNotNull(productResponse);
        assertEquals("Test Product", productResponse.name());


        verify(productRepository, times(1)).findById(1L);
    }
}
