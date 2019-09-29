package com.stackroute.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class Entity {

    @Id
    private String entityId;
    private String entityName;
    private String entityCategory;
    private String entitySubCategory;
    private String entityDescription;
    private String entityImageUrl;
    private String entityLocation;
    private String entityPostedBy;
    private Date entityPostedOn;
    private String overAllRating;
    private Map<String, String> entityReview;
    private List<Review> reviews;

}
