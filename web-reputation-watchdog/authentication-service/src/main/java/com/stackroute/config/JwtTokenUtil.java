package com.stackroute.config;

import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


import java.io.Serializable;

/*	This class is responsible for performing JWT operations like creation and validation.
	It makes use of the io.jsonwebtoken.Jwts for achieving this
*/

@Component
public class JwtTokenUtil implements Serializable {

    /*  serialVersionUID is a unique identifier for Serializable classes.
        This is used during the deserialization of an object,
        to ensure that a loaded class is compatible with the serialized object.
     */
    private static final long serialVersionUID = -2550185165626007488L;

    //    To specify the duration of validation of a JWT token
    public static final long JWT_TOKEN_VALIDITY = 60 * 60 * 60;
    @Value("${jwt.secret}")
    private String secret;

    //To retrieve username from jwt token
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    //To retrieve expiration date and time from jwt token
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    //To retrieve any information from token, the secret key is needed
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
    }

    //To check if the token has expired
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    //To generate token for user
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername());
    }

    /*  while creating the token -
        1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
        2. Sign the JWT using the HS512 algorithm and secret key.
        3. According to JWS Compact Serialization
            (https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
            compaction of the JWT to a URL-safe string
   */

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, this.secret).compact();
    }

    //To validate JWT token
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
