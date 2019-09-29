package com.stackroute.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
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

public class Review {
  private String reviewTitle;
  private String reviewDescription;
  private String reviewedBy;
  private Date reviewedOn;
  private boolean genuine;
  private int upVote;
  private int downVote;
  private List<String> upVoters;
  private List<String> downVoters;

}
