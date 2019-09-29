package com.stackroute.service;

import com.stackroute.domain.Review;

public interface AddReviewService {

    public Review postReview(Review reviewDTO) throws Exception;
}
