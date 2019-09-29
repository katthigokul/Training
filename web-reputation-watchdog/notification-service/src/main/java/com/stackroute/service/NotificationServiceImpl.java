package com.stackroute.service;

import com.stackroute.domain.Notification;
import com.stackroute.dto.NotificationDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 * @Service indicates annotated class is a service which hold business logic in the Service layer
 */
@Service
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    private SimpMessagingTemplate template;

    /**
     * Constructor based Dependency injection to inject SimpMessagingTemplate
     */
    @Autowired
    public NotificationServiceImpl(SimpMessagingTemplate template) {
        this.template = template;
    }

    /**
     * @RabbitListener to consume messages from entity queue
     */
    @RabbitListener(queues = "${notification.rabbitmq.queue}")

    public void receivedNotifications(NotificationDto notificationDto) {
        log.info(notificationDto.toString());
        Notification notification = new Notification();
        notification.setEmailId(notificationDto.getEntityPostedBy());
        notification.setEntityName(notificationDto.getEntityName());
        /**The return value is broadcast to users who subscribes to "/queue/notification"*/
        notification.setNotificationMessage("New review is added to "+notificationDto.getEntityName());
        this.template.convertAndSendToUser(notification.getEmailId(), "/queue/notification",notification.getNotificationMessage());
    }
}

