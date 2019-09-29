package com.stackroute.controller;

import com.stackroute.domain.Entity;
import com.stackroute.domain.UserProfile;
import com.stackroute.dto.UserProfileDto;
import com.stackroute.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * RestController annotation is used to create Restful web services using Spring MVC
 */
@RestController
/**
 * RequestMapping annotation maps HTTP requests to handler methods
 */
@RequestMapping("api/v1")
@CrossOrigin(origins = "*")
public class UserProfileController {

    private UserProfileService userProfileService;

    public UserProfileController() {
    }

    @Autowired
    public UserProfileController(UserProfileService userProfileService) {

        this.userProfileService = userProfileService;
    }

    /**
     * PostMapping Annotation for posting user profile data
     */
    @PostMapping("user")
    public ResponseEntity<?> saveUserProfile(@RequestBody UserProfileDto userProfileDto) throws Exception {
        return new ResponseEntity<>(userProfileService.saveUserProfile(userProfileDto), HttpStatus.CREATED);
    }

    /**
     * GetMapping Annotation for getting user profiles data
     */
    @GetMapping("users")
    public ResponseEntity<?> getUser() throws Exception {
        System.out.println("Get Controller");
        List<UserProfile> retrieveUsers = userProfileService.getUser();
        return new ResponseEntity<>(retrieveUsers, HttpStatus.OK);
    }

    /**
     * GetMapping Annotation for getting user profile data by emailId
     */
    @GetMapping("user/{emailId}")
    public ResponseEntity<?> getUserProfileByEmailId(@PathVariable String emailId) throws Exception {

        UserProfile retrieveUserProfileByName = userProfileService.getUserProfileByEmailId(emailId);
        return new ResponseEntity<>(retrieveUserProfileByName, HttpStatus.OK);
    }

    /**
     * PutMapping Annotation for updating user profile data
     */
    @PutMapping("/user")
    public ResponseEntity<?> updateUserProfile(@RequestBody UserProfile userProfile) throws Exception {

        UserProfile updatedUserProfile = userProfileService.updateTheProfile(userProfile);
        return new ResponseEntity<>(updatedUserProfile, HttpStatus.OK);
    }

    @GetMapping("userentities/{emailId}")
    public ResponseEntity<?> getMyEntitiesByEmailId(@PathVariable String emailId) throws Exception {
        List<Entity> retrieveUserEntitiesByName = userProfileService.getMyEntitiesByEmailId(emailId);
        return new ResponseEntity<>(retrieveUserEntitiesByName, HttpStatus.OK);
    }

    @PostMapping("user/{emailId}")
    public ResponseEntity<?> updateMyUserScore(@PathVariable String emailId, @PathVariable Boolean vote) throws Exception {
        userProfileService.getMyEntitiesByEmailId(emailId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}