package com.stackroute.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * Indicates this as Configuration class
 */
@Configuration
/**Enables WebSocket message handling, backed by a message broker.*/
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

    /**
     * Register Stomp endpoints: the url to open the WebSocket connection.
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        /**Register the "/web-socket" endpoint, enabling the SockJS protocol.
         SockJS is used (both client and server side) to allow alternative messaging options if WebSocket is not available.*/
        registry.addEndpoint("/web-socket")
                /**To allow server to receive requests from any origin*/
                .setAllowedOrigins("*")
                .withSockJS();
    }

    /**
     * Configure the message broker.
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        /** Enable a simple memory-based message broker to send messages to the client on destinations prefixed with "/app".
         Simple message broker handles subscription requests from clients, stores
         them in memory, and broadcasts messages to connected clients with
         matching destinations.*/
        registry.setApplicationDestinationPrefixes("/app");
        registry.enableSimpleBroker("/queue", "/user");
        registry.setUserDestinationPrefix("/user");

    }
}
