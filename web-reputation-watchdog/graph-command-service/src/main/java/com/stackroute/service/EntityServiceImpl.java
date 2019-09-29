package com.stackroute.service;

import com.stackroute.domain.Entity;
import com.stackroute.dto.EntityDto;
import com.stackroute.exceptions.EntityAlreadyExistsException;
import com.stackroute.repository.EntityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service annotated class indicates it is a service which hold business logic in the Service layer
 */
@Service
@Slf4j
public class EntityServiceImpl implements EntityService {

    private EntityRepository entityRepository;

    /**
     * Constructor based Dependency injection to inject EntityRepository here
     */
    @Autowired
    public EntityServiceImpl(EntityRepository entityRepository) {
        this.entityRepository = entityRepository;
    }

    /**
     * RabbitListener to consume messages from entity queue
     */
    @RabbitListener(queues = "${entity.rabbitmq.queue}")
    public void saveEntity(EntityDto entityDto) {
//        if (entityRepository.existsById(entityDto.getEntityId())) {
//            throw new EntityAlreadyExistsException();
//        }
        if (entityDto.getEntityId() != null) {
            Entity entity = new Entity();
            entity.setEntityId(entityDto.getEntityId());
            log.info(entity.getEntityId());
            entity.setEntityName(entityDto.getEntityName());
            entity.setEntityDescription(entityDto.getEntityDescription());
            entity.setEntityImageUrl(entityDto.getEntityImageUrl());
            entity.setEntityLocation(entityDto.getEntityLocation());
            //Saves entity in neo4j database
            entityRepository.save(entity);
            //Creates Entity POSTED_BY User relation
            entityRepository.setPostedRelation(entityDto.getEntityPostedBy(), entityDto.getEntityId());
            //Creates Entity IS_A SubDomain relation
            entityRepository.setIsARelation(entityDto.getEntityId(), entityDto.getEntitySubCategory());
            log.info(String.valueOf(entity));
        }
    }

    /**
     * RabbitListener to consume messages from entityDelete queue
     */
    @RabbitListener(queues = "${entityDelete.rabbitmq.queue}")
    public void deleteEntity(EntityDto entityDto) {
        //Deletes Entity POSTED_BY User relation
        entityRepository.deletePostedRelation(entityDto.getEntityPostedBy(), entityDto.getEntityId());
    }

}