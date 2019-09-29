package com.stackroute.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * With @Data, Lombok will generate getter and setter methods, toString methods, Equal & Hashcode methods
 */
@Data

/**@NoArgsConstructor will add constructor with no arguments*/
@NoArgsConstructor

/**@AllArgsConstructor will add constructor with all properties in the class*/
@AllArgsConstructor
public class NotificationDto {

    private String entityName;
    private String entityPostedBy;
}
