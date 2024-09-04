package com.sonata.Service;

import com.sonata.Model.Review;
import com.sonata.Repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    // Create a new review
    public Review createReview(Review review) {
        return reviewRepository.save(review);
    }
    
    // Create multiple reviews
    public List<Review> createMultipleReviews(List<Review> reviews) {
        return reviewRepository.saveAll(reviews);
    }
    // Get all reviews
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    // Get all reviews by product ID
    public List<Review> getReviewsByProduct(Long productId) {
        return reviewRepository.findByProduct(productId);
    }

    // Get average rating by product ID
    public Double getAverageRatingByProduct(Long productId) {
        return reviewRepository.findAverageRatingByProduct(productId);
    }

    // Get all reviews by user ID
    public List<Review> getReviewsByUser(Long userId) {
        return reviewRepository.findByUser(userId);
    }
}
