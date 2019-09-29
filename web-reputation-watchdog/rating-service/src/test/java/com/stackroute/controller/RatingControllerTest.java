//package com.stackroute.controller;
//
//import com.stackroute.domain.Review;
//import com.stackroute.service.RatingService;
//import com.stackroute.service.SentimentReport;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//import static org.mockito.internal.verification.VerificationModeFactory.times;
//
///**
// * @RunWith(SpringRunner.class) is used to provide a bridge between Spring Boot test features and JUnit
// */
//@RunWith(SpringRunner.class)
//@WebMvcTest
//
//public class RatingControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//    private Review review;
//    /**
//     * Create a mock for TrackService
//     */
//    @MockBean
//    private RatingService ratingService;
//    /**
//     * Inject the mocks as dependencies into TrackController
//     */
//    @InjectMocks
//    private RatingController ratingController;
//
//    /**
//     * Run this before each test case
//     */
//    private List<Review> list = null;
//    private List<SentimentReport> sentimentReports;
//
//    @Before
//    public void setUp() {
//        //Initialising the mock object
//        MockitoAnnotations.initMocks(this);
//        mockMvc = MockMvcBuilders.standaloneSetup(ratingController).build();
//        //act
//        review = new Review();
//        Map map = new HashMap();
//        map.put("food", "3.6");
//        map.put("service", "4.0");
//        review.setEntityReview(map);
//        review.setGenuine("True");
//        review.setReviewDescription("The service is awesome but drink is excellent");
//        review.setReviewedBy("pushkar@gmail.com");
//        review.setReviewTitle("The Food is Awesome");
//        review.setReviewerScore(250);
//        review.setTitle("Mcdonalds");
//        list = new ArrayList<>();
//        list.add(review);
//    }
//
//    @After
//    public void tearDown() {
//        this.review = null;
//        this.list = null;
//    }
//
//    @Test
//    public void givenPostMappingUrlShouldThrowError() throws Exception {
//        when(ratingService.getReviewAndSendRating(any())).thenReturn(sentimentReports);
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/review")
//                .contentType(MediaType.ALL).content(asJsonString(review)))
//                .andDo(MockMvcResultHandlers.print());
//        //verify here verifies that ratingService getReviewAndSendRating method is only called once
//        verify(ratingService, times(0)).getReviewAndSendRating(review);
//
//    }
//
//    @Test
//    public void givenPostMappingUrlShouldReturnReview() throws Exception {
//        when(ratingService.getReviewAndSendRating(any())).thenReturn(sentimentReports);
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/review")
//                .contentType(MediaType.ALL).content(asJsonString(review)))
//                .andDo(MockMvcResultHandlers.print());
//    }
//
//    private byte[] asJsonString(Review review) {
//        return null;
//    }
//
//}
