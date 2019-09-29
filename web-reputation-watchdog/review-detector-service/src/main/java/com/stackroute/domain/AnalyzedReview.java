package com.stackroute.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/*  Data bundles the features of @ToString, @EqualsAndHashCode, @Getter/@Setter
    and @RequiredArgsConstructor together
*/
@Data

// It generates constructor with no parameters
@NoArgsConstructor

// It generates a constructor with 1 parameter for each field
@AllArgsConstructor

public class AnalyzedReview {
    private String entityId;
    private String entityTitle;
    private Map<String, String> entityReview;
    private String reviewTitle;
    private String reviewDescription;
    private String reviewedBy;
    private int reviewerScore;
    private boolean genuine;
}
