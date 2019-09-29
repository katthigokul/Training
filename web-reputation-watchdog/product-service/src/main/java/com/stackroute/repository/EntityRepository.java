package com.stackroute.repository;

import com.stackroute.domain.Entity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EntityRepository extends MongoRepository<Entity, String> {

    public List<Entity> findByEntityName(String entityName);

    public List<Entity> findByEntityCategory(String entityCategory);

    public Entity findByEntityId(String entityId);

}
