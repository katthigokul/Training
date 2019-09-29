package com.stackroute.repository;

import com.stackroute.domain.Entity;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface RecommendationRepository extends Neo4jRepository<Entity, Long> {


    //    Recommend an entity/entities to review to an user who has reviewed an entity of the same sub-domain before
    @Query("MATCH (user:User)-[r:REVIEWED]->(rev:Review)<-[h:HAS_A]-(entity:Entity)- [i:IS_A]->(s:SubDomain)" +
            "<-[is:IS_A]-(entities:Entity) WHERE user.emailId={emailId} return entities LIMIT 5")
    List<Entity> findEntitiesOfSameSubDomainByReviewerEmailId(@Param("emailId") String emailId);


    //    Recommend an entity/entities to review to an user who has posted an entity of the same sub-domain before
    @Query("MATCH (user:User)-[r:POSTED]->(entity:Entity)-[is:IS_A]->(s:SubDomain)<-[i:IS_A]-(entities:Entity) " +
            "WHERE user.emailId={emailId} return entities LIMIT 5")
    List<Entity> findEntitiesOfSameSubDomainByPosterEmailId(@Param("emailId") String emailId);

}
