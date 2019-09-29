package com.stackroute.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ReviewDTO {
    private Map<String, String> aspectReview;
    private String reviewTitle;
    private LocalDateTime reviewedOn;
    private boolean genuine;

}
