package com.stackroute.domain;

import java.io.Serializable;

public class JwtResponse implements Serializable {

    /*  serialVersionUID is a unique identifier for Serializable classes.
        This is used during the deserialization of an object,
        to ensure that a loaded class is compatible with the serialized object.
     */
    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;

    public JwtResponse(String jwttoken) {
        this.jwttoken = jwttoken;
    }

    public String getToken() {
        return this.jwttoken;
    }
}
