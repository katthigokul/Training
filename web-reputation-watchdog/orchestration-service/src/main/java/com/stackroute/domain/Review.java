package com.stackroute.domain;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import org.springframework.stereotype.Component;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@ToString
public class Review {
    private String entityId;
    private String entityTitle;
    private Map<String, String> entityReview;
    private String reviewTitle;
    private String reviewDescription;
    private String reviewedBy;
    private int reviewerScore;
    private boolean genuine;


}
