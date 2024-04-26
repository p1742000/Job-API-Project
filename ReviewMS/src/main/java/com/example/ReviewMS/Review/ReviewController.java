package com.example.ReviewMS.Review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews(@RequestParam Long companyId){
        List<Review> reviews = reviewService.getAllReviews(companyId);
        if(reviews != null) return new ResponseEntity<>(reviews, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<String> createReviews(@RequestParam Long companyId, @RequestBody Review reviewDetails){
        boolean response = reviewService.createReviews(companyId, reviewDetails);
        if(response) return new ResponseEntity<>("Review Created Successfully. ", HttpStatus.CREATED);
        return new ResponseEntity<>("Company that you are looking for to add review is not found.", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{Id}")
    public ResponseEntity<Review> getReviewsById(@PathVariable Long Id){
        Review response = reviewService.getReviewById(Id);
        if( response != null) return new ResponseEntity<>(response, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{Id}")
    public ResponseEntity<String> updateReviewsById(@PathVariable Long Id, @RequestBody Review updatedDetails){
        boolean response = reviewService.updateReviewsById(Id, updatedDetails);
        if(response) return new ResponseEntity<>("Review Edited Successfully.", HttpStatus.OK);
        return new ResponseEntity<>("Review or company that you are looking for is not found.", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{Id}")
    public ResponseEntity<String> deleteReviews(@PathVariable Long Id){
        boolean response = reviewService.deleteReview(Id);
        if(response) return new ResponseEntity<>("Review is Deleted successfully. ", HttpStatus.OK);
        return new ResponseEntity<>("Review that you want to delete is not found.", HttpStatus.NOT_FOUND);
    }
}
