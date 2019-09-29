package com.stackroute.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

import java.util.List;

/**
 * @NodeEntity to indicate that this class is directly mapped to a node in Neo4j.
 */
@NodeEntity

/**With @Data, Lombok will generate getter and setter methods, toString methods, Equal & Hashcode methods*/
@Data

/**@NoArgsConstructor will add constructor with no arguments*/
@NoArgsConstructor

/**@AllArgsConstructor will add constructor with all properties in the class*/
@AllArgsConstructor

public class UserDto {

    /**
     * @Id annotation make id variable as Primary key
     */
    @Id
    private String emailId;
    private String firstName;
    private String lastName;
    private double mobileNumber;
    private List<ReviewDto> myReviews;
    private List<EntityDto> myEntities;
    private double userScore;
}