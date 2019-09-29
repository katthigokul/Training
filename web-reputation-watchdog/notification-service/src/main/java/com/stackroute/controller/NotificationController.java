package com.stackroute.controller;

import com.stackroute.domain.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

/**
 * RestController annotation is used to create Restful web services using Spring MVC
 */

@Controller
public class NotificationController {

    /**
     * Constructor based Dependency injection to inject SimpMessagingTemplate into controller
     */
    @Autowired
    private SimpMessagingTemplate template;

    /**
     * The @MessageMapping annotation ensures that if a notification is sent to destination "/notification",
     * then the sendNotification() method is called.
     */
    @MessageMapping("/notification")

    /**The return value is broadcast to all subscribers to "/user" as specified in the SendTo*/

    private void sendNotification(Notification notification) {
        this.template.convertAndSendToUser(notification.getEmailId(), "/queue/notification", notification.getNotificationMessage());
    }

}
