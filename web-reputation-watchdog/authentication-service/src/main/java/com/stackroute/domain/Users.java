package com.stackroute.domain;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Entity defines that a class can be mapped to a table
@Entity

@Table(name = "authentication_details")

/*  Data bundles the features of @ToString, @EqualsAndHashCode, @Getter/@Setter
    and @RequiredArgsConstructor together
*/
@Data

// It generates constructor with no parameters
@NoArgsConstructor

// It generates a constructor with 1 parameter for each field
@AllArgsConstructor

public class Users {
    //  @Id indicates that field below is the primary key of current entity
    @Id

    /*  The @GeneratedValue annotation is to configure the way of increment of the specified
        column(field) i.e. for auto_increment in the definition of table to make it self-incremental
    */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String emailId;

    @Column
    @JsonIgnore
    private String password;
}
