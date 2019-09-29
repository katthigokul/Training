package com.stackroute.config;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/*  Component annotation is used to denote this class as Component. It means that Spring framework
    will autodetect this class for dependency injection when annotation-based configuration
    and classpath scanning is used
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {
    /*  serialVersionUID is a unique identifier for Serializable classes.
        This is used during the deserialization of an object, to ensure that
        a loaded class is compatible with the serialized object.
     */
    private static final long serialVersionUID = -7858869558953243875L;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }
}
