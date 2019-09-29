package com.stackroute.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration

public class RabbitMqConfig {

//    RabbitMq configuration for Consuming Entity details

    /**
     * @Value injects values from properties file into fields
     */
    @Value("${entity.rabbitmq.queue}")
    String queueName;
    @Value("${entity.rabbitmq.exchange}")
    String exchange;
    @Value("${entity.rabbitmq.routingkey}")
    String routingkey;

    @Bean
    Queue queue() {
        return new Queue(queueName, true);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(exchange);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingkey);
    }

//    RabbitMq configuration for producing notification details

    /**
     * @Value injects values from properties file into fields
     */
    @Value("${notification.rabbitmq.queue}")
    String notificationQueue;
    @Value("${notification.rabbitmq.exchange}")
    String notificationExchange;
    @Value("${notification.rabbitmq.routingkey}")
    String notificationRoutingkey;

    @Bean
    Queue notificationQueue() {
        return new Queue(notificationQueue, true);
    }

    @Bean
    TopicExchange notificationExchange() {
        return new TopicExchange(notificationExchange);
    }

    @Bean
    Binding notificationBinding(Queue notificationQueue, TopicExchange notificationExchange) {
        return BindingBuilder.bind(notificationQueue).to(notificationExchange).with(notificationRoutingkey);
    }

    //    RabbitMq configuration for Producing deleted Entity details

    /**
     * @Value injects values from properties file into fields
     */
    @Value("${deleteEntity.rabbitmq.queue}")
    String queueNameDelete;
    @Value("${deleteEntity.rabbitmq.exchange}")
    String exchangeDelete;
    @Value("${deleteEntity.rabbitmq.routingkey}")
    String routingkeyDelete;

    @Bean
    Queue queueDelete() {
        return new Queue(queueNameDelete, true);
    }

    @Bean
    TopicExchange exchangeDelete() {
        return new TopicExchange(exchangeDelete);
    }

    @Bean
    Binding bindingDelete(Queue queueDelete, TopicExchange exchangeDelete) {
        return BindingBuilder.bind(queueDelete).to(exchangeDelete).with(routingkeyDelete);
    }

    //    RabbitMq configuration for Producing Review details

    /**
     * @Value injects values from properties file into fields
     */
    @Value("${review.rabbitmq.queue}")
    String reviewQueueName;
    @Value("${review.rabbitmq.exchange}")
    String reviewExchange;
    @Value("${review.rabbitmq.routingkey}")
    String reviewRoutingkey;

    @Bean
    Queue queueReview() {
        return new Queue(reviewQueueName, true);
    }

    @Bean
    TopicExchange exchangeReview() {
        return new TopicExchange(reviewExchange);
    }

    @Bean
    Binding bindingReview(Queue queueReview, TopicExchange exchangeReview) {
        return BindingBuilder.bind(queueReview).to(exchangeReview).with(reviewRoutingkey);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    ConnectionFactory connectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory("localhost");
        cachingConnectionFactory.setUsername("guest");
        cachingConnectionFactory.setPassword("guest");
        return cachingConnectionFactory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }

}
