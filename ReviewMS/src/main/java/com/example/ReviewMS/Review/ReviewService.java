package com.example.ReviewMS.Review;

import java.util.List;

public interface ReviewService {

    List<Review> getAllReviews(Long companyId);

    Boolean createReviews(Long companyId, Review reviewDetails);

    Review getReviewById(Long reviewId);

    Boolean updateReviewsById(Long reviewId, Review reviewDetails);

    Boolean deleteReview(Long reviewId);
}
