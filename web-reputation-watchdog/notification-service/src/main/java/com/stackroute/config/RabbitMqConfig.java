package com.stackroute.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Indicates this as Configuration class
 */
@Configuration
public class RabbitMqConfig {

    //RabbitMq configuration for binding notification queue with its exchange

    /**
     * @Value injects values from properties file into fields
     */
    @Value("${notification.rabbitmq.queue}")
    private String queue;

    @Value("${notification.rabbitmq.exchange}")
    private String exchange;

    @Value("${notification.rabbitmq.routingkey}")
    private String routingKey;

    /**
     * Creating a bean for the notification queue
     */
    @Bean
    public Queue queue() {
        return new Queue(queue, true);
    }

    /**
     * Creating a bean for the Exchange of notification queue
     */
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchange);
    }

    /**
     * Binding between Exchange and Queue using routing key
     */
    @Bean
    public Binding bindEntityQueue(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingKey);
    }

    /**
     * The bean  Jackson2JsonMessageConverter will deserialize the JSON messages to Java classes
     */
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
