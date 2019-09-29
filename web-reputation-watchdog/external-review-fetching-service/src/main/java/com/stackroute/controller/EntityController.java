package com.stackroute.controller;

import com.stackroute.domain.Movie;
import com.stackroute.domain.ResponseType;
import com.stackroute.domain.RestaurantHolder;
import com.stackroute.domain.ZomatoAPIResponse;
import com.stackroute.dto.EntityDto;
import com.stackroute.exception.MovieNotFoundException;
import com.stackroute.exception.RestaurantNotFoundException;
import com.stackroute.service.MovieService;
import com.stackroute.service.RestaurantService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
/**
 * @RestController annotation is used to create Restful web services using Spring MVC
 */
@RestController
/**
 * @RequestMapping annotation maps HTTP requests to handler methods
 */
@RequestMapping("/api/v1")

public class EntityController {

    private static final Logger logger = LoggerFactory.getLogger(EntityController.class);
    private MovieService movieService;
    private RestaurantService restaurantService;
    private Environment environment;
    private ResponseType responseType;
    private List<RestaurantHolder> restaurants = null;


    /**
     * Constructor based Dependency injection to inject ExternalReviewFetchingService into controller
     */
    @Autowired
    public EntityController(MovieService movieService, RestaurantService restaurantService, Environment environment) {
        this.movieService = movieService;
        this.restaurantService = restaurantService;
        this.environment = environment;
    }

    /**
     * @GetMapping Annotation for mapping HTTP GET requests for getting data for Restaurant and Movie from handler methods.
     * This method takes input from product-service according to domain specified and searches using the query string in the respective
     * restaurant or movie API and returns the list of results of EntityDto type.
     */
    @GetMapping("search/{domainName}/{queryString}")
    public ResponseEntity<?> getEntityByTitle(@PathVariable String domainName, @PathVariable String queryString) throws Exception {
        String apiKey = environment.getProperty("app.api.key");
        List<EntityDto> entityDtoList = null;
        entityDtoList = new ArrayList<>();
        this.restaurants = new ArrayList<>();
        responseType = new ResponseType();
        ZomatoAPIResponse storeRestaurant = null;
        Movie storeMovie = null;

        if (domainName.toLowerCase().contentEquals("restaurant")) {
            try {
                storeRestaurant = restaurantService.searchRestaurantByName(queryString);
            } catch (RestaurantNotFoundException rnfe) {
                rnfe.printStackTrace();
            }
        } else {
            try {
                storeMovie = movieService.searchMovieByTitle(apiKey, queryString);
            } catch (MovieNotFoundException mnfe) {
                mnfe.printStackTrace();
            }
        }

        queryString = queryString.replace('-', ' ');

        if (storeMovie != null && storeMovie.getTitle().toLowerCase().contains(queryString.toLowerCase())) {
            EntityDto movieEntityDto = new EntityDto();
            movieEntityDto.setEntityName(storeMovie.getTitle());
            movieEntityDto.setEntityDescription(storeMovie.getDescription());
            movieEntityDto.setEntityImageUrl(storeMovie.getImg());
            movieEntityDto.setOverAllRating(storeMovie.getImdbRating());
            movieEntityDto.setHighlights(null);
            movieEntityDto.setAddress(null);
            entityDtoList.add(movieEntityDto);
            this.responseType.setMovie(storeMovie);
        }

        if (storeRestaurant != null) {
            for (RestaurantHolder restaurantHolder : storeRestaurant.getRestaurants()) {
                if (restaurantHolder.getRestaurant().getName().toLowerCase().contains(queryString.toLowerCase())) {
                    EntityDto restaurantEntityDto = new EntityDto();
                    restaurantEntityDto.setEntityName(restaurantHolder.getRestaurant().getName());
                    restaurantEntityDto.setEntityDescription(null);
                    restaurantEntityDto.setEntityImageUrl(restaurantHolder.getRestaurant().getThumb());
                    restaurantEntityDto.setOverAllRating(restaurantHolder.getRestaurant().getUser_rating().getAggregate_rating());
                    restaurantEntityDto.setHighlights(Arrays.toString(restaurantHolder.getRestaurant().getHighlights()));
                    restaurantEntityDto.setAddress(restaurantHolder.getRestaurant().getLocation().getAddress());
                    restaurantEntityDto.setCity(restaurantHolder.getRestaurant().getLocation().getCity());
                    restaurantEntityDto.setLocality(restaurantHolder.getRestaurant().getLocation().getLocality());
                    entityDtoList.add(restaurantEntityDto);
                    restaurants.add(restaurantHolder);
                }
            }
//            RestaurantHolder[] restaurantArray = new RestaurantHolder[restaurants.size()];
//            for (int i = 0; i < restaurants.size(); i++) {
//                restaurantArray[i] = restaurants.get(i);
//            }
//            this.responseType.setRestaurants(restaurantArray);
        }
        return new ResponseEntity<List<EntityDto>>(entityDtoList, HttpStatus.OK);
    }
}
