package com.stackroute.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.AssertFalse;
import java.time.LocalDateTime;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class RatingServiceDTO {
    private String entityId;
    private String entityTitle;
    private Map<String, String> entityReview;
    private String reviewTitle;
    private String reviewDescription;
    private String reviewedBy;
    private int reviewerScore;
    private boolean genuine;

}
