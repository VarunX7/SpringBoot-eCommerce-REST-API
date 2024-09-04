package com.sonata.Controller;

import com.sonata.Model.Review;
import com.sonata.Service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    // Create a new review
    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody Review review) {
        Review createdReview = reviewService.createReview(review);
        return new ResponseEntity<>(createdReview, HttpStatus.CREATED);
    }
    
    @PostMapping("/createMultiple")
    public ResponseEntity<List<Review>> createMultipleReviews(@RequestBody List<Review> reviews) {
        List<Review> createdReviews = reviewService.createMultipleReviews(reviews);
        return new ResponseEntity<>(createdReviews, HttpStatus.CREATED);
    }
    
    // Get all reviews
    @GetMapping("/all")
    public ResponseEntity<List<Review>> getAllReviews() {
        List<Review> reviews = reviewService.getAllReviews();
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }
    
    // Get all reviews by product ID
    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Review>> getReviewsByProduct(@PathVariable Long productId) {
        List<Review> reviews = reviewService.getReviewsByProduct(productId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    // Get average rating by product ID
    @GetMapping("/product/{productId}/average-rating")
    public ResponseEntity<Double> getAverageRatingByProduct(@PathVariable Long productId) {
        Double averageRating = reviewService.getAverageRatingByProduct(productId);
        return new ResponseEntity<>(averageRating, HttpStatus.OK);
    }

    // Get all reviews by user ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Review>> getReviewsByUser(@PathVariable Long userId) {
        List<Review> reviews = reviewService.getReviewsByUser(userId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }
}
