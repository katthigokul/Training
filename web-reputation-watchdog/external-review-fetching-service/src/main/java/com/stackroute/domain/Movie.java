package com.stackroute.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * With @Data, Lombok will generate getter and setter methods, toString methods, Equal & Hashcode methods
 */
@Data
/*
 *@NoArgsConstructor will generate constructor with no arguments
 * */
@NoArgsConstructor
/*
 *@AllArgsConstructor will generate constructor with all properties in the class
 * */
@AllArgsConstructor
public class Movie {
    /**
     * @JsonProperty annotation will indicate the property name in JSON.
     */
    @JsonProperty("Title")
    private String Title;
    @JsonProperty("Released")
    private String releaseDate;
    @JsonProperty("Genre")
    private String genre;
    @JsonProperty("Plot")
    private String description;
    @JsonProperty("Poster")
    private String img;
    @JsonProperty("imdbRating")
    private String imdbRating;
    @JsonProperty("imdbVotes")
    private String imdbVotes;
}
