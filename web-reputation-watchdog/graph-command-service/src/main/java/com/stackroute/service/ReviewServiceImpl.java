package com.stackroute.service;

import com.stackroute.domain.Review;
import com.stackroute.dto.ReviewDto;
import com.stackroute.repository.ReviewRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Service indicates annotated class is a service which hold business logic in the Service layer
 */
@Service
@Slf4j
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;

    /**
     * Constructor based Dependency injection to inject ReviewRepository here
     */
    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    /**
     * @RabbitListener to consume messages from review queue
     */
    @RabbitListener(queues = "${review.rabbitmq.queueR}")
    public void receivedReview(ReviewDto reviewDto) {
        Review review = new Review();
        review.setReviewTitle(reviewDto.getReviewTitle());
        review.setReviewDescription(reviewDto.getReviewDescription());
        //Saves review details in neo4j database
        reviewRepository.save(review);
        //Creates Entity HAS_A Review relation
        reviewRepository.setHasARelation(reviewDto.getEntityId(), reviewDto.getReviewTitle());
        reviewRepository.setReviewedRelation(reviewDto.getReviewedBy(), reviewDto.getReviewTitle());
        log.info(String.valueOf(review));
    }
}