package com.stackroute.service;

import com.stackroute.domain.Entity;

import java.util.List;

public interface RecommendationService {
    //    Recommend an entity/entities to review to an user who has reviewed an entity of the same sub-domain before
    List<Entity> findEntitiesOfSameSubDomainByReviewerEmailId(String emailId);

    //    Recommend an entity/entities to review to an user who has posted an entity of the same sub-domain before
    List<Entity> findEntitiesOfSameSubDomainByPosterEmailId(String emailId);
}
