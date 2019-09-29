package com.stackroute.service;

import com.stackroute.domain.Entity;
import com.stackroute.repository.RecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendationServiceImpl implements RecommendationService {
    private RecommendationRepository recommendationRepository;

    @Autowired
    public RecommendationServiceImpl(RecommendationRepository repository) {
        this.recommendationRepository = repository;
    }

    //    Recommend an entity/entities to review to an user who has reviewed an entity of the same sub-domain before
    @Override
    public List<Entity> findEntitiesOfSameSubDomainByReviewerEmailId(String emailId) {
        return recommendationRepository.findEntitiesOfSameSubDomainByReviewerEmailId(emailId);
    }

    //    Recommend an entity/entities to review to an user who has posted an entity of the same sub-domain before
    @Override
    public List<Entity> findEntitiesOfSameSubDomainByPosterEmailId(String emailId) {
        return recommendationRepository.findEntitiesOfSameSubDomainByPosterEmailId(emailId);
    }

}
