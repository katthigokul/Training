package com.stackroute.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {

    @Id
//    private Long reviewId;
//    private UUID reviewId;
    private String reviewTitle;
//    private String emailId;
    private String reviewDescription;
    private String reviewedBy;
    private Date reviewedOn;
    private boolean genuine;
    private List<String> upVoters;
    private List<String> downVoters;

}
