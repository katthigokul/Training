package com.stackroute.controller;


import com.stackroute.service.AddReviewService;
import com.stackroute.domain.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

// This annotation is applied to a class to mark it as a request handler.
@RestController

//This annotation maps HTTP requests to handler methods of REST controllers
@RequestMapping(value = "/api/v1")

// Cross-origin resource sharing (CORS) is a standard protocol that defines the interaction between a browser and a server for safely handling cross-origin HTTP requests
@CrossOrigin(origins = "*")
public class AddReviewController {

    private AddReviewService addReviewService;

    @Autowired
    public AddReviewController(AddReviewService addReviewService) {
        this.addReviewService = addReviewService;
    }

//  Controller method to post a review to an entity
    @PostMapping(value = "/addReview")
    public ResponseEntity<?> postReview(@RequestBody Review reviewDTO) throws Exception {
//        reviewDTO.setEntityId("5d88702");
        System.out.println(reviewDTO.getEntityId());
        System.out.println(reviewDTO);
        System.out.println(reviewDTO.getEntityId()+"getting data from fronted");
        Review graphQueryDTO = addReviewService.postReview(reviewDTO);
        return new ResponseEntity<>(graphQueryDTO, HttpStatus.OK);
    }
}
