package com.stackroute.dto;

import com.stackroute.domain.Review;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@Component
public class EntityDto {

    private String entityName;
    private String entityImageUrl;
    private String overAllRating;
    private String entityDescription;
    private String highlights;
    private String address;
    private String locality;
    private String city;

    private String entityId;
    private String entityCategory;
    private String entitySubCategory;
    private String entityPostedBy;
    private Date entityPostedOn;
    private Map<String, String> entityReview;
    private List<Review> reviews;

}
