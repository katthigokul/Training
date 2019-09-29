package com.stackroute.service;

import com.stackroute.domain.Movie;
import com.stackroute.dto.EntityDto;
import com.stackroute.exception.MovieNotFoundException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.ArrayList;
import java.util.List;

/**
 * @Service indicates annotated class is a service which hold business logic in the Service layer
 */
@Service
public class MovieServiceImpl implements MovieService {


    private static final String OMDB_API_BASE_URL = "http://omdbapi.com";
    private final WebClient webClient;

//    @Autowired
//    private AmqpTemplate amqpTemplate;
//
//    @Value("${externalReview.rabbitmq.exchange}")
//    private String exchange;
//
//    @Value("${externalReview.rabbitmq.routingkey}")
//    private String routingkey;

    public MovieServiceImpl() {
        this.webClient = WebClient.builder()
                .baseUrl(OMDB_API_BASE_URL)
                .build();
    }

    /**
     * Implementation of searchMovieByTitle method
     */
    @Override
    public Movie searchMovieByTitle(String apiKey, String title) throws MovieNotFoundException {

        Movie movie = webClient.get()
                .uri("/?apikey=" + apiKey + "&t=" + title)
                .retrieve()
                .bodyToMono(Movie.class)
                .block();

        if (movie.getTitle() == null) {
            throw new MovieNotFoundException("Please Enter a Valid Movie Name!");
        } else {
//            List<EntityDto> list = new ArrayList<>();
//            EntityDto movieEntityDto = new EntityDto();
//            movieEntityDto.setEntityName(movie.getTitle());
//            movieEntityDto.setDescription(movie.getDescription());
//            movieEntityDto.setPhotoUrl(movie.getImg());
//            movieEntityDto.setRating(movie.getImdbRating());
//            movieEntityDto.setHighlights(null);
//            movieEntityDto.setAddress(null);
//            movieEntityDto.setLocality(null);
//            movieEntityDto.setCity(null);
//            movieEntityDto.setEmail(email);
//            list.add(movieEntityDto);
//            amqpTemplate.convertAndSend(exchange, routingkey, list);

            return webClient.get()
                    .uri("/?apikey=" + apiKey + "&t=" + title)
                    .retrieve()
                    .bodyToMono(Movie.class)
                    .block();
        }
    }
}
