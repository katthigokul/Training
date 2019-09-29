package com.stackroute.service;

import com.stackroute.domain.Entity;
import com.stackroute.domain.UserProfile;
import com.stackroute.dto.UserProfileDto;
import com.stackroute.exceptions.UserProfileAlreadyExistsException;
import com.stackroute.exceptions.UserProfileNotFoundException;
import com.stackroute.repository.UserProfileRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Service indicates annotated class is a service which hold business logic in the Service layer
 */
@Service
public class UserProfileServiceImpl implements UserProfileService {
    private UserProfileRepository userProfileRepository;
    private RabbitTemplate amqpTemplate;


    /**
     * Constructor based Dependency injection to inject UserprofileRepository here
     */
    @Autowired
    public UserProfileServiceImpl(UserProfileRepository userProfileRepository, RabbitTemplate amqpTemplate) {
        this.userProfileRepository = userProfileRepository;
        this.amqpTemplate = amqpTemplate;
    }

    @Value("${userProfileRegister.rabbitmq.queue}")
    public String queueName;

    @Value("${userProfileRegister.rabbitmq.exchange}")
    public String exchange;

    @Value("${userProfileRegister.rabbitmq.routingkey}")
    public String routingkey;

    @Value("${userprofileProfile.rabbitmq.exchange}")
    String profileExchange;

    @Value("${userprofileProfile.rabbitmq.routingkey}")
    String profileRoutingkey;

    @Value("${userprofileProfile.rabbitmq.queue}")
    String profileQueueName;

    @Value("${user.rabbitmq.queueR}")
    private String userQueue;

    @Value("${user.rabbitmq.exchangeR}")
    private String userExchange;

    @Value("${user.rabbitmq.routingkeyR}")
    private String userRoutingKey;

    /**
     * Implementation of saveUserProfile method for saving user details
     */


    @Override
    public UserProfile saveUserProfile(UserProfileDto userProfileDto) throws UserProfileAlreadyExistsException {

        amqpTemplate.convertAndSend(exchange, routingkey, userProfileDto);

        UserProfile userProfile = new UserProfile();
        userProfile.setFirstName(userProfileDto.getFirstName());
        userProfile.setLastName(userProfileDto.getLastName());
        userProfile.setEmailId(userProfileDto.getEmailId());
        userProfile.setMobileNumber(userProfileDto.getMobileNumber());
        amqpTemplate.convertAndSend(userExchange,userRoutingKey,userProfile);
        System.out.println("sent to recommandation"+userProfile.toString());
        return userProfileRepository.save(userProfile);
    }

    /**
     * Implementation of getUser method for getting user details
     */
    @Override
    public List<UserProfile> getUser() throws Exception {
        /**Throws Exception if Database connection issue happens*/
        System.out.println("GetUser method");
        try {
            System.out.println(userProfileRepository.findAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userProfileRepository.findAll();
    }

    /**
     * Implementation of getUserProfileByEmailId method for getting user details by emailId
     */
    @Override
    public UserProfile getUserProfileByEmailId(String email) {

        return userProfileRepository.findByEmailId(email);
    }


    /**
     * Implementation of updateUserProfile method for updating user details
     */
    @Override
    public UserProfile updateTheProfile(UserProfile userProfile) throws UserProfileNotFoundException {
        UserProfile getUserProfile = userProfileRepository.findById(userProfile.getEmailId()).get();
        System.out.println(getUserProfile.toString());
        getUserProfile.setFirstName(userProfile.getFirstName());
        getUserProfile.setLastName(userProfile.getLastName());
        getUserProfile.setMobileNumber(userProfile.getMobileNumber());
        getUserProfile.setMyEntities(userProfile.getMyEntities());
        getUserProfile.setMyReviews(userProfile.getMyReviews());
        getUserProfile.setUserScore(userProfile.getUserScore());
        System.out.println(userProfile.toString());
        UserProfile updateUserProfile = userProfileRepository.save(userProfile);
        amqpTemplate.convertAndSend(profileExchange, profileRoutingkey, userProfile);
        return updateUserProfile;

    }

    @Override
    public List<Entity> getMyEntitiesByEmailId(String emailId) {
        UserProfile getMyEntities = userProfileRepository.findByEmailId(emailId);
        return getMyEntities.getMyEntities();
    }

    @Override
    public void updateMyScore(String emailId, Boolean flag) {
        UserProfile updateMyScore = userProfileRepository.findByEmailId(emailId);
        if (flag = true) {
            updateMyScore.setUserScore(updateMyScore.getUserScore() + 5.0);
            userProfileRepository.save(updateMyScore);
        } else {
            updateMyScore.setUserScore(updateMyScore.getUserScore() - 2.0);
            userProfileRepository.save(updateMyScore);
        }

    }

}




