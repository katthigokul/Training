package com.stackroute.service;

import com.stackroute.domain.User;
import com.stackroute.dto.UserDto;
import com.stackroute.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Service indicates annotated class is a service which hold business logic in the Service layer
 */
@Service
@Slf4j
public class UserServiceImpl {

    private UserRepository userRepository;

    /**
     * Constructor based Dependency injection to inject UserRepository here
     */
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    /**
     * @RabbitListener to consume messages from user queue
     */
    @RabbitListener(queues = "${user.rabbitmq.queueR}")
    public void receivedUser(UserDto userDto) {
        System.out.println(userRepository.findByEmailId(userDto.getEmailId()));
        System.out.println(userDto.getEmailId());
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setEmailId(userDto.getEmailId());
        log.info("User: " + user);
        //Saves User details consumed from user queue in neo4j database
        userRepository.save(user);
        log.info(String.valueOf(user));
    }
}
