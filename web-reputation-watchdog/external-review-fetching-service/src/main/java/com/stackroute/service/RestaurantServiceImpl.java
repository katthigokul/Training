package com.stackroute.service;

import com.stackroute.domain.RestaurantHolder;
import com.stackroute.domain.ZomatoAPIResponse;
import com.stackroute.dto.EntityDto;
import com.stackroute.exception.RestaurantNotFoundException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Service indicates annotated class is a service which hold business logic in the Service layer
 */
@Service
public class RestaurantServiceImpl implements RestaurantService {

    private static final String ZOMATO_API_BASE_URL = "https://developers.zomato.com/api/v2.1";
    private final WebClient webClient;

//    @Autowired
//    private AmqpTemplate amqpTemplate;
//
//    @Value("${externalReview.rabbitmq.exchange}")
//    private String exchange;
//
//    @Value("${externalReview.rabbitmq.routingkey}")
//    private String routingkey;

    public RestaurantServiceImpl() {
        this.webClient = WebClient.builder()
                .baseUrl(ZOMATO_API_BASE_URL)
                .defaultHeader("user-key", "022860ef904b791bbeecc090fb45cc0a")
                .defaultHeader("Accept", "application/json")
                .build();
    }

    /**
     * Implementation of searchRestaurantByName method
     */
    @Override
    public ZomatoAPIResponse searchRestaurantByName(String title) throws RestaurantNotFoundException {

        ZomatoAPIResponse zomatoAPIResponse = webClient.get()
                .uri("/search?q=" + title)
                .retrieve()
                .bodyToMono(ZomatoAPIResponse.class)
                .block();

        if (zomatoAPIResponse.getRestaurants().length == 0) {
            throw new RestaurantNotFoundException("Oops!! Restaurant Not Available");
        } else {
//            List<EntityDto> list = new ArrayList<>();
//            EntityDto restaurantEntityDto;
//
//            for (RestaurantHolder restaurantHolder : zomatoAPIResponse.getRestaurants()) {
//                restaurantEntityDto = new EntityDto();
//                restaurantEntityDto.setRating(restaurantHolder.getRestaurant().getUser_rating().getAggregate_rating());
//                restaurantEntityDto.setAddress(restaurantHolder.getRestaurant().getLocation().getAddress());
//                restaurantEntityDto.setLocality(restaurantHolder.getRestaurant().getLocation().getLocality());
//                restaurantEntityDto.setCity(restaurantHolder.getRestaurant().getLocation().getCity());
//                restaurantEntityDto.setHighlights(Arrays.toString(restaurantHolder.getRestaurant().getHighlights()));
//                restaurantEntityDto.setEntityName(restaurantHolder.getRestaurant().getName());
//                restaurantEntityDto.setPhotoUrl(restaurantHolder.getRestaurant().getThumb());
//                restaurantEntityDto.setEmail(email);
//                list.add(restaurantEntityDto);
//            }
//            amqpTemplate.convertAndSend(exchange, routingkey, list);

            return webClient.get()
                    .uri("/search?q=" + title)
                    .retrieve()
                    .bodyToMono(ZomatoAPIResponse.class)
                    .block();
        }
    }
}
