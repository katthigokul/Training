package com.stackroute;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/*	@SpringBootApplication annotation is used to mark a configuration class that
	declares one or more @Bean methods and also triggers auto-configuration and component scanning
 */
@SpringBootApplication

//	@EnableDiscoveryClient annotation is to make the service as a Discoverable client
@EnableDiscoveryClient

/*	Embedded Zuul proxy can be enabled with the @EnableZuulProxy annotation.
	This turns the Gateway application into a reverse proxy that
	forwards relevant calls to other services
 */
@EnableZuulProxy
public class JwtAuthenticationApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwtAuthenticationApplication.class, args);
    }

}
