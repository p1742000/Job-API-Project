package com.example.ReviewMS.Review.Impl;


import com.example.ReviewMS.Review.Review;
import com.example.ReviewMS.Review.ReviewRepository;
import com.example.ReviewMS.Review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

//    public ReviewServiceImpl() {
//    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
       if(reviews != null) return reviews;
        return null;
    }

    @Override
    public Boolean createReviews(Long companyId, Review reviewDetails) {
        Review review = reviewRepository.findById(companyId).orElse(null);
        if(review != null){
            reviewDetails.setCompanyId(companyId);
            reviewRepository.save(reviewDetails);
            return true;
        }
        return false;
    }

    @Override
    public Review getReviewById(Long reviewId) {
            return reviewRepository.findById(reviewId).orElse(null);
    }

    @Override
    public Boolean updateReviewsById(Long reviewId, Review reviewDetails) {

        Review review = reviewRepository.findById(reviewId).get();
        if (review != null){
            review.setTitle(reviewDetails.getTitle());
            review.setDescription(reviewDetails.getDescription());
            review.setRating(reviewDetails.getRating());
            review.setCompanyId(reviewDetails.getCompanyId());
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public Boolean deleteReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if(review != null){
            reviewRepository.delete(review);
            return true;
        }
        return false;
    }


}
