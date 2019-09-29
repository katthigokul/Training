//package com.stackroute.controller;
//
//import com.stackroute.domain.UserProfile;
//import com.stackroute.exceptions.GlobalException;
//import com.stackroute.service.UserProfileService;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.Assert.*;
///**
// * @RunWith(SpringRunner.class) is used to provide a bridge between Spring Boot test features and JUnit
// */
//@RunWith(SpringRunner.class)
//@WebMvcTest
//public class UserProfileControllerTest {
//  @Autowired
//  private MockMvc mockMvc;
//  private UserProfile userProfile;
//  /**
//   * Create a mock for TrackService
//   */
//  @MockBean
//  private UserProfileService userProfileService;
//  /**
//   * Inject the mocks as dependencies into TrackController
//   */
//  @InjectMocks
//  private UserProfileController userProfileController;
//
//  /**
//   * Run this before each test case
//   */
//  private List<UserProfile> list = null;
//
//  @Before
//  public void setUp() {
//    //Initialising the mock object
//    MockitoAnnotations.initMocks(this);
//    mockMvc = MockMvcBuilders.standaloneSetup(userProfileController).setControllerAdvice(new GlobalException()).build();
//    //act
//    userProfile = new UserProfile();
//
//    userProfile.setFirstName("venkata sai");
//    userProfile.setLastName("atmakuri");
//    userProfile.setEmailId("atmakurikeerthika@gmail.com");
////    userProfile.setMyReviews("product is nice");
////    userProfile.setMyEntities("different products");
//    list = new ArrayList<>();
//    list.add(userProfile);
//  }
//
//  @After
//  public void tearDown() {
//    this.userProfile = null;
//    this.list = null;
//  }
//
//
//}
