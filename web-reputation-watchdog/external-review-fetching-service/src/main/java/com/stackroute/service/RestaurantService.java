package com.stackroute.service;

import com.stackroute.domain.ZomatoAPIResponse;
import com.stackroute.exception.RestaurantNotFoundException;

public interface RestaurantService {

    /**
     * Abstract Method to search a Restaurant
     */
    public ZomatoAPIResponse searchRestaurantByName(String title) throws RestaurantNotFoundException;
}
