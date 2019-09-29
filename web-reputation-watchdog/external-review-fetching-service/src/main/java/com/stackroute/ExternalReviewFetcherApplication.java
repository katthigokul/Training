package com.stackroute;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @SpringBootApplication Enables Spring Boot auto config and component scanning.
 */
@SpringBootApplication
/**
 * @EnableZuulProxy allows the UI application to make proxy calls to the REST API
 */
@EnableZuulProxy
public class ExternalReviewFetcherApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExternalReviewFetcherApplication.class, args);
    }
}
