package com.stackroute.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.domain.Review;
import com.stackroute.service.RatingService;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

/**
 * RestController annotation is used to create Restful web services using Spring MVC
 */
@RestController
/**
 * RequestMapping annotation maps HTTP requests to handler methods
 */
@RequestMapping(value = "api/v1")

// Cross-origin resource sharing (CORS) is a standard protocol that defines the interaction between a browser and a server for safely handling cross-origin HTTP requests
@CrossOrigin(origins = "*")
public class RatingController {

    private RatingService ratingService;

    /**
     * Constructor based Dependency injection to inject TrackService into controller
     */
    @Autowired
    private RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }


    /*POST mapping, where Orchestration service will send the Review*/
    @PostMapping(value = "/review")
    public Review getReviewAndSendScore(@RequestBody String review1) throws IOException, ParseException {
        System.out.println(review1);
        Review reviewData = new Review();
        TypeReference<Review> typeReference = new TypeReference<Review>() {
        };
        ObjectMapper objectMapper = new ObjectMapper();
        /**
         * Reading from JSON File, Might Be used in future
         */
//        InputStream inputStream = new FileInputStream(new File("src/main/resources/review.json"));
        Review review = objectMapper.readValue(review1, typeReference);
        if (!review.isGenuine()) {
            Review review2=review;
            ratingService.getReviewAndSendRating(review);
            return review2;
        } else {
            return ratingService.getReviewAndSendRating(review);
        }
    }

    @GetMapping(value = "/review/{entityName}")
    public ResponseEntity<Optional> getAllReviewAspectScore(@PathVariable("entityName") String entityName) {
        return new ResponseEntity<>(ratingService.getReviewAspectScore(entityName), HttpStatus.OK);

    }


}

