package com.stackroute.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
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

    //RabbitMq configuration for binding entity queue with its exchange

    /**
     * @Value injects values from properties file into fields
     */
    @Value("${review.rabbitmq.queue}")
    String queueName;
    @Value("${review.rabbitmq.exchange}")
    String exchange;
    @Value("${review.rabbitmq.routingkey}")
    String routingkey;

    /**
     * Creating a bean for the queue
     */
    @Bean
    Queue queue() {
        return new Queue(queueName, true);
    }

    /**
     * Creating a bean for the Exchange of queue
     */
    @Bean
    TopicExchange exchange() {
        return new TopicExchange(exchange);
    }


    /**
     * Binding between Exchange and Queue using routing key
     */
    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingkey);
    }


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
