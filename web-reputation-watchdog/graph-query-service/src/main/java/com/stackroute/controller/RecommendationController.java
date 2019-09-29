package com.stackroute.controller;

import com.stackroute.domain.Entity;
import com.stackroute.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1")
@CrossOrigin(origins = "*")
public class RecommendationController {
    private RecommendationService recommendationService;

    @Autowired
    public RecommendationController(RecommendationService recommentadationService) {
        this.recommendationService = recommentadationService;
    }

    //    Recommend an entity/entities to review to an user who has reviewed an entity of the same sub-domain before
    @GetMapping("/recommendMovies/{emailId}")
    public ResponseEntity<List<Entity>> getAllEntitiesOfSameSubDomainByReviewerEmailId(@PathVariable("emailId") String emailId) {
        List<Entity> entityList = new ArrayList<>();
        entityList.addAll(recommendationService.findEntitiesOfSameSubDomainByReviewerEmailId(emailId));
        return new ResponseEntity<>(entityList, HttpStatus.OK);
    }

    //    Recommend an entity/entities to review to an user who has posted an entity of the same sub-domain before
    @GetMapping("/recommendRestaurant/{emailId}")
    public ResponseEntity<List<Entity>> getAllEntitiesOfSameSubDomainByPosterEmailId(@PathVariable("emailId") String emailId) {
        List<Entity> entityList = new ArrayList<>();
        entityList.addAll(recommendationService.findEntitiesOfSameSubDomainByPosterEmailId(emailId));
        return new ResponseEntity<>(entityList, HttpStatus.OK);
    }

}
