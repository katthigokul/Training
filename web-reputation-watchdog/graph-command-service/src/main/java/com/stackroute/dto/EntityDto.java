package com.stackroute.dto;

import com.stackroute.domain.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

import java.util.Date;
import java.util.List;
import java.util.Map;

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

public class EntityDto {

    /**
     * @Id annotation make id variable as Primary key
     */
    @Id
    private String entityId;
    private String entityName;
    private String entityCategory;
    private String entitySubCategory;
    private String entityDescription;
    private String entityImageUrl;
    private String entityLocation;
    private String entityPostedBy;
    private Date entityPostedOn;
    private Double overAllRating;
    private Map<String, String> entityReview;
    private List<Review> reviews;
}