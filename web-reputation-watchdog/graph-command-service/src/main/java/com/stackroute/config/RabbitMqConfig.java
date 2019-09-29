package com.stackroute.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    //RabbitMq configuration for binding entity queue with its exchange

    /**
     * @Value injects values from properties file into fields
     */
    @Value("${entity.rabbitmq.queue}")
    private String queue;

    @Value("${entity.rabbitmq.exchange}")
    private String exchange;

    @Value("${entity.rabbitmq.routingkey}")
    private String routingKey;

    /**
     * Creating a bean for the entity queue
     */
    @Bean
    public Queue queue() {
        return new Queue(queue, true);
    }

    /**
     * Creating a bean for the Exchange of entity queue
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

    //RabbitMq configuration for binding entityDelete queue with its exchange

    /**
     * @Value injects values from properties file into fields
     */
    @Value("${entityDelete.rabbitmq.queue}")
    private String deleteQueue;

    @Value("${entityDelete.rabbitmq.exchange}")
    private String deleteExchange;

    @Value("${entityDelete.rabbitmq.routingkey}")
    private String deleteRoutingKey;

    /**
     * Creating a bean for the entityDelete queue
     */
    @Bean
    public Queue deleteQueue() {
        return new Queue(deleteQueue, true);
    }

    /**
     * Creating a bean for the Exchange of entityDelete queue
     */
    @Bean
    public TopicExchange deleteExchange() {
        return new TopicExchange(deleteExchange);
    }

    /**
     * Binding between Exchange and Queue using routing key
     */
    @Bean
    public Binding bindEntityDeleteQueue(Queue deleteQueue, TopicExchange deleteExchange) {
        return BindingBuilder.bind(deleteQueue).to(deleteExchange).with(deleteRoutingKey);
    }


    //RabbitMq configuration for binding review queue with its exchange

    /**
     * @Value injects values from properties file into fields
     */
    @Value("${review.rabbitmq.queueR}")
    private String reviewQueue;

    @Value("${review.rabbitmq.exchangeR}")
    private String reviewExchange;

    @Value("${review.rabbitmq.routingkeyR}")
    private String reviewRoutingKey;

    /**
     * Creating a bean for the review queue
     */
    @Bean
    public Queue reviewQueue() {
        return new Queue(reviewQueue, true);
    }

    /**
     * Creating a bean for the Exchange of review queue
     */
    @Bean
    public TopicExchange reviewExchange() {
        return new TopicExchange(reviewExchange);
    }

    /**
     * Binding between Exchange and Queue using routing key
     */
    @Bean
    public Binding bindReviewQueue(Queue reviewQueue, TopicExchange reviewExchange) {
        return BindingBuilder.bind(reviewQueue).to(reviewExchange).with(reviewRoutingKey);
    }


    //RabbitMq configuration for binding user queue with its exchange

    /**
     * @Value injects values from properties file into fields
     */
    @Value("${user.rabbitmq.queueR}")
    private String userQueue;

    @Value("${user.rabbitmq.exchangeR}")
    private String userExchange;

    @Value("${user.rabbitmq.routingkeyR}")
    private String userRoutingKey;

    /**
     * Creating a bean for the user queue
     */
    @Bean
    public Queue userQueue() {
        return new Queue(userQueue, true);
    }

    /**
     * Creating a bean for the Exchange of user queue
     */
    @Bean
    public TopicExchange userExchange() {
        return new TopicExchange(userExchange);
    }

    /**
     * Binding between Exchange and Queue using routing key
     */
    @Bean
    public Binding bindUserQueue(Queue userQueue, TopicExchange userExchange) {
        return BindingBuilder.bind(userQueue).to(userExchange).with(userRoutingKey);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }


}
