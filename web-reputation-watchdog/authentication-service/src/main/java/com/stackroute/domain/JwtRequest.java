package com.stackroute.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/*  Data bundles the features of @ToString, @EqualsAndHashCode, @Getter/@Setter
    and @RequiredArgsConstructor together
 */
@Data

// It generates constructor with no parameters
@NoArgsConstructor

// It generates a constructor with 1 parameter for each field
@AllArgsConstructor
public class JwtRequest implements Serializable {

    private String emailId;
    private String password;
}
