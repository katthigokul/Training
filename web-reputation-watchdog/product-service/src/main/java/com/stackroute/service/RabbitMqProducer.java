package com.stackroute.service;

import com.stackroute.domain.Entity;
import com.stackroute.dto.NotificationDTO;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class RabbitMqProducer {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Value("${entity.rabbitmq.exchange}")
    private String exchange;

    @Value("${entity.rabbitmq.routingkey}")
    private String routingkey;

    public void send(Entity entity) {
        amqpTemplate.convertAndSend(exchange, routingkey,entity);
    }

    @Value("${deleteEntity.rabbitmq.exchange}")
    private String exchangeDelete;

    @Value("${deleteEntity.rabbitmq.routingkey}")
    private String routingkeyDelete;

    public void delete(Entity entityName) {
        amqpTemplate.convertAndSend(exchangeDelete, routingkeyDelete,entityName);
    }

    @Value("${notification.rabbitmq.exchange}")
    private String notificationExchange;

    @Value("${notification.rabbitmq.routingkey}")
    private String notificationRoutingkey;

    public void sendUpdatedEntity(NotificationDTO notificationDTO) {
        amqpTemplate.convertAndSend(notificationExchange, notificationRoutingkey,notificationDTO);
    }
}