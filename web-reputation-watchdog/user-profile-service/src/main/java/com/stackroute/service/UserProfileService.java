package com.stackroute.service;

import com.stackroute.domain.Entity;
import com.stackroute.domain.UserProfile;
import com.stackroute.dto.UserProfileDto;
import com.stackroute.exceptions.UserProfileAlreadyExistsException;
import com.stackroute.exceptions.UserProfileNotFoundException;

import java.util.List;

public interface UserProfileService {
  /**
   * AbstractMethod to save a Userprofile
   */

  public UserProfile saveUserProfile(UserProfileDto userProfileDto) throws UserProfileAlreadyExistsException;

  /**
   * AbstractMethod to get all users
   */
  public List<UserProfile> getUser() throws Exception;

  /**
   * AbstractMethod to update comments of a profile by its id
   */
  public UserProfile updateTheProfile(UserProfile userProfile) throws UserProfileNotFoundException;


  /**
   * AbstractMethod to get userprofile by email
   */
  public UserProfile getUserProfileByEmailId(String emailId) throws UserProfileNotFoundException;
  public  List<Entity> getMyEntitiesByEmailId(String emailId);
  public void updateMyScore(String emailId, Boolean vote);
}





