package com.stackroute.repository;

import com.stackroute.dto.EntityDTO;
import com.stackroute.dto.ReviewDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends MongoRepository<EntityDTO, String> {
}
