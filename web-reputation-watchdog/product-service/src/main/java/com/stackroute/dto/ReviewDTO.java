package com.stackroute.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ReviewDTO {

//    private UUID reviewId;
    private String entityName;
    private String entityId;
    private String reviewTitle;
    private String reviewDescription;
    private String reviewedBy;
    private Date reviewedOn;
    private boolean genuine;
    private String overAllRating;
    private Map<String, String> entityReview = new HashMap<>();

}
