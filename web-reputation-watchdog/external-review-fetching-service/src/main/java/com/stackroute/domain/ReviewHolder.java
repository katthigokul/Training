package com.stackroute.domain;

import lombok.*;

/*
 *@Data, Lombok will generate getter and setter methods, toString methods, Equal & Hashcode methods
 * */
@Data
/*
 *@NoArgsConstructor will generate constructor with no arguments
 * */
@NoArgsConstructor
/*
 *For each instance variable, @getter annotation to let lombok generate the default getter
 * */
@Getter
/*
 *For each instance variable, @setter annotation to let lombok generate the default setter
 * */
@Setter
/*
 *@ToString annotation returns the string representation of the object
 * */
@ToString
public class ReviewHolder {

    private Review review;
}
