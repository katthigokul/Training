package com.stackroute.domain;

import lombok.Data;

/*
 *With @Data, Lombok will generate getter and setter methods, toString methods, Equal & Hashcode methods
 * */
@Data
public class ResponseType {

    private Movie movie;
    private RestaurantHolder[] restaurants;
}
