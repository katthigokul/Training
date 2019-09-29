package com.stackroute;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * With this annotation, this artifact will act like a Zuul service proxy and will enable all the features of a API gateway layer
 */
@EnableZuulProxy
@EnableEurekaClient
/**
 * Enables Spring Boot auto config and component scanning.
 */
@SpringBootApplication
public class GraphCommandServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GraphCommandServiceApplication.class, args);
    }

}
