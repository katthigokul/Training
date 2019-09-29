package com.stackroute.service;

import java.util.ArrayList;

import com.stackroute.domain.Users;
import com.stackroute.domain.UsersDTO;
import com.stackroute.repository.UsersDetailsRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

// @Service annotation is used to mark the class as a service provider which holds business logic
@Service

public class AuthenticationService implements UserDetailsService, RabbitListenerConfigurer {

    //    @Autowired enables to inject the object dependency implicitly.
    @Autowired
    private UsersDetailsRepository usersDetailsRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    //    To find user by its username to get its details and to throw exception if not found
    @Override
    public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {
        Users users = usersDetailsRepository.findByEmailId(emailId);
        if (users == null) {
            throw new UsernameNotFoundException("User not found with Email ID: " + emailId);
        }
        return new org.springframework.security.core.userdetails.User(users.getEmailId(), users.getPassword(),
                new ArrayList<>());
    }

    //    This is to consume messages from queue produced by Producer
    @RabbitListener(queues = "${jsa.rabbitmq.queue}")
    public void receiveUserDetails(UsersDTO userDetails) {
        Users newUser = new Users();
        newUser.setEmailId(userDetails.getEmailId());
        newUser.setPassword(passwordEncoder.encode(userDetails.getPassword()));
        usersDetailsRepository.save(newUser);
    }

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {

    }
}

