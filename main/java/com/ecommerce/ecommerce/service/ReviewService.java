package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.request.ReviewRequest;
import com.ecommerce.ecommerce.dto.response.ReviewResponse;

import java.util.List;

public interface ReviewService {


    ReviewResponse addReview(Long productId, Long userId, ReviewRequest request);


    ReviewResponse updateReview(Long reviewId, ReviewRequest request);


    void deleteReview(Long reviewId);


    List<ReviewResponse> getReviewsByProductId(Long productId);
}
