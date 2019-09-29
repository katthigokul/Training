package com.stackroute.repository;

import com.stackroute.domain.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

/**
 * @Repository marks the specific class as a Data Access Object
 */
@Repository
public interface UserRepository extends Neo4jRepository<User, Long> {

    /**findByEmailId method to get user by its emailId*/
    public User findByEmailId(String emailId);
}