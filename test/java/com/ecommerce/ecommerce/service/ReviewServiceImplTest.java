package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.request.ReviewRequest;
import com.ecommerce.ecommerce.dto.response.ReviewResponse;
import com.ecommerce.ecommerce.entity.Product;
import com.ecommerce.ecommerce.entity.Review;
import com.ecommerce.ecommerce.entity.User;
import com.ecommerce.ecommerce.repository.ReviewRepository;
import com.ecommerce.ecommerce.repository.ProductRepository;
import com.ecommerce.ecommerce.repository.UserRepository;
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

public class ReviewServiceImplTest {

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ReviewServiceImpl reviewService;

    private Product product;
    private User user;
    private Review review;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        product = new Product();
        product.setId(1L);
        product.setName("Test Product");

        user = new User();
        user.setId(1L);
        user.setUsername("testuser");

        review = new Review();
        review.setId(1L);
        review.setProduct(product);
        review.setUser(user);
        review.setRating(4);
        review.setComment("Great product!");
    }

    @Test
    public void testAddReview() {

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(reviewRepository.save(any(Review.class))).thenReturn(review);


        ReviewRequest reviewRequest = new ReviewRequest(4, "Great product!");


        ReviewResponse reviewResponse = reviewService.addReview(1L, 1L, reviewRequest);


        assertNotNull(reviewResponse);
        assertEquals(4, reviewResponse.rating());
        assertEquals("Great product!", reviewResponse.comment());
        assertEquals(1L, reviewResponse.productId());
        assertEquals(1L, reviewResponse.userId());


        verify(productRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).findById(1L);
        verify(reviewRepository, times(1)).save(any(Review.class));
    }

    @Test
    public void testUpdateReview() {

        when(reviewRepository.findById(1L)).thenReturn(Optional.of(review));
        when(reviewRepository.save(any(Review.class))).thenReturn(review);


        ReviewRequest reviewRequest = new ReviewRequest(5, "Updated comment!");
        ReviewResponse updatedReviewResponse = reviewService.updateReview(1L, reviewRequest);
        assertNotNull(updatedReviewResponse);
        assertEquals(5, updatedReviewResponse.rating());
        assertEquals("Updated comment!", updatedReviewResponse.comment());


        verify(reviewRepository, times(1)).findById(1L);
        verify(reviewRepository, times(1)).save(any(Review.class));
    }

    @Test
    public void testDeleteReview() {

        when(reviewRepository.findById(1L)).thenReturn(Optional.of(review));


        reviewService.deleteReview(1L);


        verify(reviewRepository, times(1)).findById(1L);
        verify(reviewRepository, times(1)).delete(any(Review.class));
    }

}
