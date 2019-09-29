package com.stackroute.repository;

import com.stackroute.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersDetailsRepository extends JpaRepository<Users, Integer> {

    //    to get details of user by its emailId
    Users findByEmailId(String emailId);
}
