package com.stackroute.service;

import com.stackroute.domain.Movie;
import com.stackroute.exception.MovieNotFoundException;

public interface MovieService {

    /**
     * Abstract Method to search a Movie
     */
    public Movie searchMovieByTitle(String apiKey, String title) throws MovieNotFoundException;
}
