package com.stackroute.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.util.List;


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
@Component

public class UserProfile {
  /**
   * Id annotation makes id variable as Primary key
   */
  @Id
  private String emailId;
  private String firstName;
  private String lastName;
  private double mobileNumber;
  private List<Review> myReviews;
  private List<Entity> myEntities;
  private double userScore;

}




