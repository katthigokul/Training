package com.stackroute.dto;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.util.List;

@Document(collection = "EntityDto")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EntityDTO {
    @Id
    private String entityName;
    private List<ReviewDTO> reviewDTOList;
}
