package com.stackroute.service;

import com.stackroute.domain.AnalyzedReview;
import com.stackroute.dto.ReviewDto;

public interface ReviewDetectorService {

    /*    To get the review details from orchestration-service and to send it back with a tag
          genuine-true/false after analysis
    */
    AnalyzedReview getReviewAndSendAnalyzedReview(ReviewDto reviewDto);
}
