package com.stackroute.repository;

import com.stackroute.domain.Review;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @Repository marks the specific class as a Data Access Object
 */
@Repository
public interface ReviewRepository extends Neo4jRepository<Review, Long> {

    /**Query Annotation used for defining custom queries*/

    //Creates Entity HAS_A Review relationship by taking entityId from Entity node and reviewId from Review node
    @Query("MATCH(entity:Entity) WITH entity MATCH(review:Review) WHERE entity.entityId={entityId} and review.reviewTitle={reviewTitle} CREATE (entity)-[:HAS_A]->(review) RETURN entity")
    public Review setHasARelation(@Param("entityId") String entityId, @Param("reviewTitle") String reviewTitle);

    //Creates User REVIEWED Review relationship by taking emailId from User node and reviewTitle from Review node
    @Query("MATCH(user:User) WITH user MATCH(review:Review) WHERE user.emailId={emailId} and review.reviewTitle={reviewTitle} CREATE (user)-[:REVIEWED]->(review) RETURN user")
    public Review setReviewedRelation(@Param("emailId") String firstName, @Param("reviewTitle") String reviewTitle);
}