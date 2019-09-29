package com.stackroute.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Component
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
