package com.stackroute.dto;

import lombok.*;
import org.springframework.stereotype.Component;



@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class UserProfileDto {

  private String emailId;
  private String password;
  private String firstName;
  private String lastName;
  private double mobileNumber;
}
