//package com.wrw.erfs.ExternalReviewFetchingService.service;
//
//import org.assertj.core.api.Assertions;
//import org.junit.Before;
//import org.junit.FixMethodOrder;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.junit.runners.MethodSorters;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.reactive.server.WebTestClient;
//
//import java.time.Duration;
//
///**
// * @RunWith(SpringRunner.class) is used to provide a bridge between Spring Boot test features and JUnit
// */
//@RunWith(SpringRunner.class)
///**
// * With the @SpringBootTest annotation, Spring Boot provides a convenient way to start up an application context to be used in a test
// */
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
///**
// * JUnit provides @FixMethodOrder annotation to have a control on the order of execution of unit tests
// */
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//public class RestaurantServiceImplTest {
//
//    /**
//     * @Autowired annotation is used for automatic dependency injection.
//     */
//    @Autowired
//    private WebTestClient webTestClient;
//
//    /**
//     * Run this before each test case
//     */
//    @Before
//    public void setUp() {
//        webTestClient = webTestClient.mutate().responseTimeout(Duration.ofMillis(360000)).build();
//    }
//
//    /**
//     * Test annotation tells JUnit that the public void method to which it is attached can be run as a test case
//     */
//    @Test
//    public void givenCorrectUrlShouldCheckForPositiveResponse() {
//        webTestClient.get()
//                .uri("/api/v1/restaurant/{name}", "kfc")
//                .exchange()
//                .expectStatus().isOk()
//                .expectBody()
//                .consumeWith(response ->
//                        Assertions.assertThat(response.getResponseBody()).isNotNull());
//    }
//
//    /**
//     * Test annotation tells JUnit that the public void method to which it is attached can be run as a test case
//     */
//    @Test
//    public void givenIncorrectUrlShouldCheckForNegativeResponse() {
//        webTestClient.get()
//                .uri("/api/v1/restaurants/{name}", "weqweqweqwr")
//                .exchange()
//                .expectStatus().isNotFound()
//                .expectBody()
//                .consumeWith(response ->
//                        Assertions.assertThat(response.getResponseBody()).isNotNull());
//    }
//}