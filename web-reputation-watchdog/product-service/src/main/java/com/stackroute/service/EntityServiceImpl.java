package com.stackroute.service;

import com.stackroute.domain.Entity;
import com.stackroute.domain.Review;
import com.stackroute.dto.EntityDto;
import com.stackroute.dto.NotificationDTO;
import com.stackroute.dto.ReviewDTO;
import com.stackroute.exceptions.EntityNotFoundException;
import com.stackroute.repository.EntityRepository;
import com.stackroute.repository.ReviewRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class EntityServiceImpl implements EntityService {

    private EntityRepository entityRepository;
    private RabbitMqProducer rabbitMqProducer;
    private ReviewRepository reviewRepository;
    private Entity entity;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    public EntityServiceImpl(EntityRepository entityRepository, RabbitMqProducer rabbitMqProducer, ReviewRepository reviewRepository) {
        this.entityRepository = entityRepository;
        this.rabbitMqProducer = rabbitMqProducer;
        this.reviewRepository = reviewRepository;
    }

    // Override Method to save an entity when an entity is posted
    @Override
    public Entity saveEntity(Entity entity) {
        Map<String, String> aspectBasedRating = new HashMap<String, String>();
        if (entity.getEntityCategory().toLowerCase().equals("restaurant")) {
            aspectBasedRating.put("Atmosphere", "3");
            aspectBasedRating.put("Service", "3");
            aspectBasedRating.put("Value For Money", "3");
            aspectBasedRating.put("Taste", "3");
            entity.setEntityReview(aspectBasedRating);
        } else if (entity.getEntityCategory().toLowerCase().equals("movie")) {
            aspectBasedRating.put("Plot", "3");
            aspectBasedRating.put("Performance", "3");
            aspectBasedRating.put("Music", "3");
            aspectBasedRating.put("Cinematography", "3");
            entity.setEntityReview(aspectBasedRating);
        }
        Entity savedEntity = entityRepository.save(entity);
        rabbitMqProducer.send(savedEntity);
        return savedEntity;
    }

    /**
     * Override method to search an entity.
     * If the entity is present in the database, it will return the entity data.
     * Id not, it will call the external-review-fetching-service and then will return the data from external-service
     */
    @Override
    public List<EntityDto> getEntityByName(String entityName, String emailId, String entityCategory) throws EntityNotFoundException {
        /**For guest user*/
        List<Entity> entities = null;
        List<EntityDto> entityDtos = new ArrayList<>();
        List<Entity> list = new ArrayList<>();
        if ((entityCategory.toLowerCase()).equals("guest")) {
            entities = entityRepository.findByEntityName(entityName);
            for (int i = 0; i < entities.size(); i++) {
                EntityDto entityDto = new EntityDto();
                entityDto.setEntityId(entities.get(i).getEntityId());
                entityDto.setEntityName(entities.get(i).getEntityName());
                entityDto.setEntityImageUrl(entities.get(i).getEntityImageUrl());
                entityDto.setOverAllRating(entities.get(i).getOverAllRating());
                entityDto.setEntityDescription(entities.get(i).getEntityDescription());
                entityDto.setCity(entities.get(i).getEntityLocation());
                entityDto.setEntityPostedBy(entities.get(i).getEntityPostedBy());
                entityDto.setEntityCategory(entities.get(i).getEntityCategory());
                entityDto.setEntitySubCategory(entities.get(i).getEntitySubCategory());
                entityDto.setEntityReview(entities.get(i).getEntityReview());
                entityDto.setEntityPostedOn(entities.get(i).getEntityPostedOn());
                entityDto.setReviews(entities.get(i).getReviews());
                entityDtos.add(entityDto);
            }
            /**For logged-in users and entity category is movie*/
        } else if ((entityCategory.toLowerCase()).equals("movie")) {
            /**Check if entity is present in our database and return it
             * else call external-review-fetching-service to get data from external-source and return it */
            if (entityRepository.findByEntityCategory(entityCategory.toLowerCase()) != null) {
                entities = entityRepository.findByEntityCategory(entityCategory.toLowerCase());
                for (int i = 0; i < entities.size(); i++) {
                    Entity entity = entities.get(i);
                    if (entity.getEntityName().equals(entityName)) {
                        EntityDto entityDto = new EntityDto();
                        entityDto.setEntityId(entity.getEntityId());
                        entityDto.setEntityName(entity.getEntityName());
                        entityDto.setEntityImageUrl(entity.getEntityImageUrl());
                        entityDto.setOverAllRating(entity.getOverAllRating());
                        entityDto.setEntityDescription(entity.getEntityDescription());
                        entityDto.setCity(entity.getEntityLocation());
                        entityDto.setEntityPostedBy(entity.getEntityPostedBy());
                        entityDto.setEntityCategory(entity.getEntityCategory());
                        entityDto.setEntitySubCategory(entity.getEntitySubCategory());
                        entityDto.setEntityReview(entity.getEntityReview());
                        entityDto.setEntityPostedOn(entity.getEntityPostedOn());
                        entityDto.setReviews(entity.getReviews());
                        entityDtos.add(entityDto);
                        list.add(entity);
                    }
                }
                entities = list;
                if (entities.size() == 0) {
                    List<EntityDto> entityDtoList = restTemplate.getForObject("http://localhost:8099/api/v1/search/" + entityCategory + "/" + entityName, ArrayList.class);
                    entityDtos = entityDtoList;
                }
            }
            /**For logged-in users and entity category is restaurant*/
        } else if ((entityCategory.toLowerCase()).equals("restaurant")) {
            /**Check if entity is present in our database and return it
             * else call external-review-fetching-service to get data from external-source and return it */
            if (entityRepository.findByEntityCategory(entityCategory.toLowerCase()) != null) {
                entities = entityRepository.findByEntityCategory(entityCategory.toLowerCase());
                for (int i = 0; i < entities.size(); i++) {
                    Entity entity = entities.get(i);
                    if (entity.getEntityName().equals(entityName)) {
                        EntityDto entityDto = new EntityDto();
                        entityDto.setEntityId(entity.getEntityId());
                        entityDto.setEntityName(entity.getEntityName());
                        entityDto.setEntityImageUrl(entity.getEntityImageUrl());
                        entityDto.setOverAllRating(entity.getOverAllRating());
                        entityDto.setEntityDescription(entity.getEntityDescription());
                        entityDto.setCity(entity.getEntityLocation());
                        entityDto.setEntityPostedBy(entity.getEntityPostedBy());
                        entityDto.setEntityCategory(entity.getEntityCategory());
                        entityDto.setEntitySubCategory(entity.getEntitySubCategory());
                        entityDto.setEntityReview(entity.getEntityReview());
                        entityDto.setEntityPostedOn(entity.getEntityPostedOn());
                        entityDto.setReviews(entity.getReviews());
                        entityDtos.add(entityDto);
                        list.add(entity);
                    }
                }
                entities = list;
                if (entities.size() == 0) {
                    List<EntityDto> entityDtoList = restTemplate.getForObject("http://localhost:8099/api/v1/search/" + entityCategory + "/" + entityName, ArrayList.class);
                    entityDtos = entityDtoList;
                }
            }
        }
//        } else {
//            throw new EntityNotFoundException();
//        }
        return entityDtos;
    }

    // Method to get recent entities by descending order of date on which the entity is posted on
    @Override
    public List<Entity> getRecentEntities() throws Exception {
        List<Entity> recentEntities = entityRepository.findAll(Sort.by(Sort.Direction.DESC, "entityPostedOn"));
        return recentEntities;
    }

    // Method to get trending entities according to number of reviews posted on an entity
    @Override
    public List<Entity> getTrendingEntities() throws Exception {
        List<Entity> trendingEntities = entityRepository.findAll(Sort.by(Sort.Direction.DESC, "overAllRating"));
        return trendingEntities;
    }

    /**
     * Method to get recent movies
     */
    @Override
    public List<Entity> getRecentMovies() throws Exception {
        String entityCategory = "movie";
        if (entityRepository.findByEntityCategory(entityCategory.toLowerCase()) != null) {
            List<Entity> recentMovies = entityRepository.findAll(Sort.by(Sort.Direction.DESC, "entityPostedOn"));
            List<Entity> list = new LinkedList<>();
            for (int i = 0; i < recentMovies.size(); i++) {
                Entity entity = recentMovies.get(i);
                if (entity.getEntityCategory().equals(entityCategory.toLowerCase())) {
                    list.add(entity);
                }
            }
            return list;
        } else return null;
    }

    /**
     * Method to get recent restaurants
     */
    @Override
    public List<Entity> getRecentRestaurants() throws Exception {
        String entityCategory = "restaurant";
        if (entityRepository.findByEntityCategory(entityCategory.toLowerCase()) != null) {
            List<Entity> recentRestaurants = entityRepository.findAll(Sort.by(Sort.Direction.DESC, "entityPostedOn"));
            List<Entity> list = new LinkedList<>();
            for (int i = 0; i < recentRestaurants.size(); i++) {
                Entity entity = recentRestaurants.get(i);
                if (entity.getEntityCategory().equals(entityCategory.toLowerCase())) {
                    list.add(entity);
                }
            }
            return list;
        } else return null;
    }

    /**
     * Method to get trending restaurants
     */
    @Override
    public List<Entity> getTrendingRestaurants() throws Exception {
        String entityCategory = "restaurant";
        if (entityRepository.findByEntityCategory(entityCategory.toLowerCase()) != null) {
            List<Entity> trendingRestaurants = entityRepository.findAll(Sort.by(Sort.Direction.DESC, "overAllRating"));
            List<Entity> list = new LinkedList<>();
            for (int i = 0; i < trendingRestaurants.size(); i++) {
                Entity entity = trendingRestaurants.get(i);
                if (entity.getEntityCategory().equals(entityCategory.toLowerCase())) {
                    list.add(entity);
                }
            }
            return list;
        } else return null;
    }

    /**
     * Method to get trending movies
     */
    @Override
    public List<Entity> getTrendingMovies() throws Exception {
        String entityCategory = "movie";
        if (entityRepository.findByEntityCategory(entityCategory.toLowerCase()) != null) {
            List<Entity> trendingMovies = entityRepository.findAll(Sort.by(Sort.Direction.DESC, "overAllRating"));
            List<Entity> list = new LinkedList<>();
            for (int i = 0; i < trendingMovies.size(); i++) {
                Entity entity = trendingMovies.get(i);
                if (entity.getEntityCategory().equals(entityCategory.toLowerCase())) {
                    list.add(entity);
                }
            }
            return list;
        } else return null;
    }

    /**
     * Method to delete entity by entityId
     */
    @Override
    public Entity deleteEntityByEntityName(String entityId) throws EntityNotFoundException {
        if (entityRepository.findByEntityId(entityId) != null) {
            Entity deletedEntity = entityRepository.findByEntityId(entityId);
            entityRepository.delete(deletedEntity);
            rabbitMqProducer.delete(deletedEntity);
            return deletedEntity;
        } else {
            throw new EntityNotFoundException("Entity you want to delete is not found");
        }
    }

    /**
     * Method to get reviews from orchestration-service and update entity
     * with reviews and send details to notification-service through notification queue
     */
    @RabbitListener(queues = "${review.rabbitmq.queue}")
    public void updateEntities(ReviewDTO reviewDTO) {
        Optional optional = entityRepository.findById(reviewDTO.getEntityId());
        System.out.println("received in product-service"+reviewDTO.toString());
        List<Review> reviewList;
        Review review1 = new Review();
        NotificationDTO notification = new NotificationDTO();
        Entity retrievedEntity;
        if (optional.isPresent()) {
            double overallRating = 0;
            retrievedEntity = entityRepository.findById(reviewDTO.getEntityId()).get();
            Map<String, String> hm = reviewDTO.getEntityReview();
            Set<String> keys = hm.keySet();
            for (String key : keys) {
                overallRating += Double.parseDouble(hm.get(key));
            }
            overallRating /= keys.size();
            retrievedEntity.setOverAllRating(Double.toString(overallRating));
            reviewList = retrievedEntity.getReviews();
            if (reviewList == null) {
                reviewList = new ArrayList<>();
            }
//            review1.setReviewId(reviewDTO.getReviewId());
            review1.setReviewTitle(reviewDTO.getReviewTitle());
            review1.setReviewDescription(reviewDTO.getReviewDescription());
            review1.setReviewedBy(reviewDTO.getReviewedBy());
            review1.setReviewedOn(reviewDTO.getReviewedOn());
            review1.setGenuine(reviewDTO.isGenuine());
            retrievedEntity.setEntityReview(reviewDTO.getEntityReview());
            reviewList.add(review1);
            retrievedEntity.setReviews(reviewList);
            entityRepository.save(retrievedEntity);
            notification.setEntityName(retrievedEntity.getEntityName());
            notification.setEntityPostedBy(retrievedEntity.getEntityPostedBy());
            rabbitMqProducer.sendUpdatedEntity(notification);
        }
    }

    /**
     * This method will be needed in future for updating upVoters and downVoters.
     */
//    @Override
//    public void updateVoters(UUID reviewId, String emailId, Boolean voted) {
//        Optional optional = reviewRepository.findByReviewId(reviewId);
//        System.out.println("******************"+reviewId);
//        Review retrievedReview;
//        voted = false;
//        List<String> upVoters;
//        List<String> downVoters;
//        if (optional.isPresent()) {
//            retrievedReview = reviewRepository.findByReviewId(reviewId).get();
//            if (voted == true) {
//                upVoters = retrievedReview.getUpVoters();
//                upVoters.add(emailId);
//            } else if (voted == false) {
//                downVoters = retrievedReview.getDownVoters();
//                downVoters.add(emailId);
//            }
//        }
//    }

}
