package com.stackroute.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;


/*  Data bundles the features of @ToString, @EqualsAndHashCode, @Getter/@Setter
    and @RequiredArgsConstructor together
*/
@Data

// It generates constructor with no parameters
@NoArgsConstructor

// It generates a constructor with 1 parameter for each field
@AllArgsConstructor

/*  Spring framework will autodetect the classes annotated with @component for
    dependency injection when annotation-based configuration and classpath scanning is used.
 */
@Component
public class ReviewDto {
    private String entityId;
    private String entityTitle;
    private Map<String, String> entityReview;
    private String reviewTitle;
    private String reviewDescription;
    private String reviewedBy;
    private int reviewerScore;
    private boolean genuine;
}
