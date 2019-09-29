package com.stackroute.service;

import com.stackroute.domain.Entity;
import com.stackroute.dto.EntityDto;
import com.stackroute.exceptions.EntityAlreadyExistsException;
import com.stackroute.exceptions.EntityNotFoundException;

import java.util.List;
import java.util.UUID;

public interface EntityService {
    /*
     * AbstractMethod to save a Entity
     */
    public Entity saveEntity(Entity entity) throws EntityAlreadyExistsException;

    /*
     * AbstractMethod to get a Entity
     */
    public List<EntityDto> getEntityByName(String entityName, String emailId, String entityCategory) throws EntityNotFoundException;

    /*
     * AbstractMethod to get a Recent Entity
     */
    public List<Entity> getRecentEntities() throws Exception;

    /*
     * AbstractMethod to get a Recent Entity
     */
    public List<Entity> getTrendingEntities() throws Exception;

    /*
     * AbstractMethod to delete an Entity
     */

    public Entity deleteEntityByEntityName(String entityName) throws EntityNotFoundException;

//    public void updateVoters(UUID reviewId, String emailId, Boolean voted);

    public List<Entity> getRecentMovies() throws Exception;

    public List<Entity> getRecentRestaurants() throws Exception;

    public List<Entity> getTrendingRestaurants() throws Exception;

    public List<Entity> getTrendingMovies() throws Exception;
}
