package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.request.ReviewRequest;
import com.ecommerce.ecommerce.dto.response.ReviewResponse;
import com.ecommerce.ecommerce.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/review")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }


    @PostMapping("/add/{productId}")
    public ReviewResponse addReview(@PathVariable Long productId, @RequestParam Long userId, @RequestBody ReviewRequest reviewRequest) {
        return reviewService.addReview(productId, userId, reviewRequest);
    }


    @PutMapping("/update/{reviewId}")
    public ReviewResponse updateReview(@PathVariable Long reviewId, @RequestBody ReviewRequest reviewRequest) {
        return reviewService.updateReview(reviewId, reviewRequest);
    }

    // Yorum silme
    @DeleteMapping("/delete/{reviewId}")
    public void deleteReview(@PathVariable Long reviewId) {
        reviewService.deleteReview(reviewId);
    }

    // Ürüne ait tüm yorumları listeleme
    @GetMapping("/product/{productId}")
    public List<ReviewResponse> getReviewsByProductId(@PathVariable Long productId) {
        return reviewService.getReviewsByProductId(productId);
    }
}
