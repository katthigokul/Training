package com.stackroute.service;



import com.stackroute.domain.ReviewRating;
import com.stackroute.dto.ReviewDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @Service indicates annotated class is a service which hold business logic in the Service layer
 */
@Service
/*
 *@Data bundles the features of @ToString, @EqualsAndHashCode, @Getter / @Setter
 * and @RequiredArgsConstructor together
 */
@Data
/**
 * @AllArgsConstructor is used to create Argument Constructor using Lombok
 */
@AllArgsConstructor
/**
 * @NoArgsConstructor is used to create No Argument Constructor using Lombok
 */
@NoArgsConstructor
public class SentimentReport {
    double sentimentScore;
//    /*
//     *Might be used in future
//     */
//    //    String sentimentType;
    ReviewRating reviewRating;
    ReviewDTO reviewDTO;
}
