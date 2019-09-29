package com.stackroute.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Document annotated class will have the ability to represent objects in the database
 */

@Document(collection = "UserProfile")

/**With @Data, Lombok will generate getter and setter methods, toString methods, Equal & Hashcode methods*/
@Data

/**@NoArgsConstructor will generate constructor with no arguments*/
@NoArgsConstructor

/**@AllArgsConstructor will generate constructor with all properties in the class*/
@AllArgsConstructor


@Builder
public class Entity {
  private String entityName;
  private String entityImageUrl;
  private String entityDescription;
  private String entityLocation;
  private String entityPostedBy;
  private Date entityPostedOn;
  private String entityCategory;
  private String entitySubCategory;
  private String location;
  private Double overAllRating;
}
