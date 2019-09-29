package com.stackroute.repository;

import com.stackroute.domain.Review;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface ReviewRepository extends MongoRepository<Review, Long> {
//    Optional<Review> findByReviewId(UUID reviewId);

}
