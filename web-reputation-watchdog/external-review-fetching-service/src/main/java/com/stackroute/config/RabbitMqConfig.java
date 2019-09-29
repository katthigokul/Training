//package com.stackroute.config;
//
//import org.springframework.amqp.core.Binding;
//import org.springframework.amqp.core.BindingBuilder;
//import org.springframework.amqp.core.Queue;
//import org.springframework.amqp.core.TopicExchange;
//import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
//import org.springframework.amqp.support.converter.MessageConverter;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @Configuration annotation indicates that the class has @Bean definition methods.
// */
//@Configuration
//public class RabbitMqConfig {
//    /**
//     * @Value annotation is used to assign default values to variables and method arguments
//     */
//    @Value("${externalReview.rabbitmq.queue}")
//    String queueName;
//    @Value("${externalReview.rabbitmq.exchange}")
//    String exchange;
//    @Value("${externalReview.rabbitmq.routingkey}")
//    String routingkey;
//
//    /**
//     * @Bean annotation tells that a method produces a bean to be managed by the Spring container.
//     */
//    @Bean
//    Queue queue() {
//        return new Queue(queueName, true);
//    }
//
//    @Bean
//    TopicExchange exchange() {
//        return new TopicExchange(exchange);
//    }
//
//    @Bean
//    Binding binding(Queue queue, TopicExchange exchange) {
//        return BindingBuilder.bind(queue).to(exchange).with(routingkey);
//    }
//
//    @Bean
//    public MessageConverter jsonMessageConverter() {
//        return new Jackson2JsonMessageConverter();
//    }
//
//    @Bean
//    ConnectionFactory connectionFactory() {
//        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory("localhost");
//        cachingConnectionFactory.setUsername("guest");
//        cachingConnectionFactory.setPassword("guest");
//        return cachingConnectionFactory;
//    }
//
//    @Bean
//    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
//        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
//        rabbitTemplate.setMessageConverter(jsonMessageConverter());
//        return rabbitTemplate;
//    }
//}
