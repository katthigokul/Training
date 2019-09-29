package com.stackroute.controller;

import com.stackroute.domain.Entity;
import com.stackroute.dto.EntityDto;
import com.stackroute.exceptions.EntityAlreadyExistsException;
import com.stackroute.exceptions.EntityNotFoundException;
import com.stackroute.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

// This annotation is applied to a class to mark it as a request handler.
@RestController

//This annotation maps HTTP requests to handler methods of REST controllers
@RequestMapping(value = "/api/v1/")

// Cross-origin resource sharing (CORS) is a standard protocol that defines the interaction between a browser and a server for safely handling cross-origin HTTP requests
@CrossOrigin(origins = "*")
public class EntityController {


    private EntityService entityService;
    private Entity entity;


    @Autowired
    public EntityController(EntityService entityService) {
        this.entityService = entityService;
    }

    @Autowired
    RestTemplate restTemplate;

    // Method to post an entity when an entity is posted
    @PostMapping("entity")
    public ResponseEntity<?> saveEntity(@RequestBody Entity entity) throws EntityAlreadyExistsException, Exception {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        Date dateObj = new Date();
        entity.setEntityPostedOn(dateObj);
        Entity savedEntity = entityService.saveEntity(entity);
        return new ResponseEntity<>(savedEntity, HttpStatus.OK);
    }

    //Method to get entity by entityName
    @GetMapping("entity/{entityName}")
    public ResponseEntity<?> getEntityByName(@PathVariable String entityName, @RequestParam String emailID, @RequestParam String entityCategory) throws EntityNotFoundException {
        List<EntityDto> retrieveEntityByName = entityService.getEntityByName(entityName, emailID, entityCategory);
        System.out.println("print" + entityName + emailID + entityCategory);
        return new ResponseEntity<>(retrieveEntityByName, HttpStatus.OK);
    }

    //Method to get recent entities
    @GetMapping("recentEntities")
    public ResponseEntity<?> getRecentEntities() throws Exception {
        List<Entity> recentEntities = entityService.getRecentEntities();
        return new ResponseEntity<>(recentEntities, HttpStatus.OK);
    }

    //Method to get trending entities
    @GetMapping("trendingEntities")
    public ResponseEntity<?> getTrendingEntities() throws Exception {
        List<Entity> trendingEntities = entityService.getTrendingEntities();
        return new ResponseEntity<>(trendingEntities, HttpStatus.OK);
    }

    // Method to delete an entity by entityName
    @DeleteMapping("deleteEntity/{entityName}")
    public ResponseEntity<?> deleteEntityByEntityName(@PathVariable String entityName) throws Exception, EntityNotFoundException {
        return new ResponseEntity<>(entityService.deleteEntityByEntityName(entityName), HttpStatus.OK);
    }

    /**
     * This method will be needed in future for updating upVoters and downVoters
     */
//    @PutMapping("updateVotes")
//    public void updateVoters(@RequestParam("reviewId") UUID reviewId, @RequestParam("emailId") String emailId, @RequestParam("voted") Boolean voted) {
//        System.out.println("**************");
//        System.out.println("reviewId + emailId + voted******"+ reviewId + emailId + voted);
//        entityService.updateVoters(reviewId, emailId, voted);
//    }

    // Method to get recent movies for logged in users

    @GetMapping("recentMovies")
    public ResponseEntity<?> recentMovies() throws Exception {
        List<Entity> recentMovies = entityService.getRecentMovies();
        return new ResponseEntity<>(recentMovies, HttpStatus.OK);
    }

    // Method to get recent restaurants for logged in users
    @GetMapping("recentRestaurants")
    public ResponseEntity<?> recentRestaurants() throws Exception {
        List<Entity> recentRestaurants = entityService.getRecentRestaurants();
        return new ResponseEntity<>(recentRestaurants, HttpStatus.OK);
    }

    // Method to get trending movies for logged in users
    @GetMapping("trendingMovies")
    public ResponseEntity<?> trendingMovies() throws Exception {
        List<Entity> trendingMovies = entityService.getTrendingMovies();
        return new ResponseEntity<>(trendingMovies, HttpStatus.OK);
    }

    // Method to get trending restaurants for logged in users
    @GetMapping("trendingRestaurants")
    public ResponseEntity<?> trendingRestaurants() throws Exception {
        List<Entity> trendingRestaurants = entityService.getTrendingRestaurants();
        return new ResponseEntity<>(trendingRestaurants, HttpStatus.OK);
    }
}
