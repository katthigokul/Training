package com.stackroute.service;

import com.stackroute.domain.Review;
import com.stackroute.dto.RatingServiceDTO;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;

//import com.stackroute.dto.GraphQueryDTO;
//import com.stackroute.dto.RatingServiceDTO;
//import org.apache.cxf.jaxrs.client.WebClient;

/**
 *
 */
@Service
public class AddReviewServiceImpl implements AddReviewService {

    //  Defining GRAPH_QUERY_URL and RATING_SERVICE_URL values
    private static final String GRAPH_QUERY_URL = "http://localhost:8020/api/v1";
    private static final String RATING_SERVICE_URL = "http://localhost:8025/api/v1";
    //creating webclient fields for webclient
    private final WebClient webClient;
    private final WebClient webClient1;
    Review review = new Review();

    //constructor for implementing the base url with webclient builder
    public AddReviewServiceImpl() {
        this.webClient = WebClient.builder()
                .baseUrl(GRAPH_QUERY_URL)
                .build();
        this.webClient1 = WebClient.builder()
                .baseUrl(RATING_SERVICE_URL)
                .build();

//        Map<String,String > entityReview =review.getEntityReview();

    }


    @Autowired
    private AmqpTemplate amqpTemplate;
    @Value("${review.rabbitmq.exchange}")
    private String exchange;

    @Value("${review.rabbitmq.routingkey}")
    private String routingkey;


    @Value("${review.rabbitmq.queue}")
    private String reviews;
//    GraphQueryDTO graphQueryDTO;

    @Value("${review.rabbitmq.exchangeR}")
    private String reviewExchange;

    @Value("${review.rabbitmq.routingkeyR}")
    private String reviewRoutingKey;

    /**
     * method for passing review to review detector method service using webclient
     * the response from webclient is sent to rating detector method if the review is geninue
     * final review is produced to rabbit mq
     */
    @Override
    public Review postReview(Review review) throws Exception {
        System.out.println("AddReviewServiceImpl : postReview :" + review.toString());
        RatingServiceDTO ratingServiceDTO = new RatingServiceDTO();
        Review review1 = new Review();
        // if (review.getEntityReview().containsValue(null)) {
        if (review.getEntityReview() == null) {
            Map<String, String> aspectBasedRating = new HashMap<String, String>();
            aspectBasedRating.put("Food", "0");
            aspectBasedRating.put("Service", "0");
            aspectBasedRating.put("Staff", "0");
            aspectBasedRating.put("Value For Money", "0");
            review.setEntityReview(aspectBasedRating);
            review1 = webClient.post()
                    .uri("/analysedReview")
                    .syncBody(review)
                    .retrieve()
                    .bodyToMono(Review.class)
                    .block();
            System.out.println("received from detector service" + review1.toString());
            ratingServiceDTO = webClient1.post()
                    .uri("/review")
                    .syncBody(review1)
                    .retrieve()
                    .bodyToMono(RatingServiceDTO.class)
                    .block();
//            rabbitMqProducer.rabbitMqProducer(ratingServiceDTO);


            System.out.println(ratingServiceDTO + "rating");
            amqpTemplate.convertAndSend(exchange, routingkey, ratingServiceDTO);
        } else {
            review1 = webClient.post()
                    .uri("/analysedReview")
                    .syncBody(review)
                    .retrieve()
                    .bodyToMono(Review.class)
                    .block();
//        movie.setGenuine(true);

            review1.setEntityId(review.getEntityId());
            ratingServiceDTO = webClient1.post()
                    .uri("/review")
                    .syncBody(review1)
                    .retrieve()
                    .bodyToMono(RatingServiceDTO.class)
                    .block();
//            rabbitMqProducer.rabbitMqProducer(ratingServiceDTO);


            System.out.println(ratingServiceDTO + "rating");
            amqpTemplate.convertAndSend(exchange, routingkey, ratingServiceDTO);
            System.out.println("sent to product service"+ratingServiceDTO.toString());
            amqpTemplate.convertAndSend(reviewExchange, reviewRoutingKey, ratingServiceDTO);
            System.out.println("sent to command service"+ratingServiceDTO.toString());



        }
        return null;
    }
}



