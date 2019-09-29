package com.stackroute.controller;

import com.stackroute.domain.AnalyzedReview;
import com.stackroute.dto.ReviewDto;
import com.stackroute.service.ReviewDetectorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//RestController annotation is used to create Restful web services using Spring MVC
@RestController

//RequestMapping annotation maps HTTP requests to handler methods
@RequestMapping(value = "api/v1")
public class ReviewDetectorController {

    private ReviewDetectorServiceImpl reviewDetectorService;

    @Autowired
    public ReviewDetectorController(ReviewDetectorServiceImpl reviewDetectorService) {
        this.reviewDetectorService = reviewDetectorService;
    }

    @PostMapping(value = "/analysedReview")
    public AnalyzedReview getReviewAndSendAnalyzedReview(@RequestBody ReviewDto reviewDto) {
        AnalyzedReview analyzedReview = new AnalyzedReview();
        analyzedReview = reviewDetectorService.getReviewAndSendAnalyzedReview(reviewDto);
        return analyzedReview;
    }
}
