package com.stackroute.repository;

import com.stackroute.domain.Entity;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @Repository marks the specific class as a Data Access Object
 */
@Repository
public interface EntityRepository extends Neo4jRepository<Entity, String> {

    /**Query Annotation used for defining custom queries*/
    //Creates Entity POSTED_BY User relationship by taking entityId from Entity node and firstName from User node
    @Query("MATCH(user:User) WITH user MATCH(entity:Entity) WHERE user.emailId={emailId} and entity.entityId={entityId} CREATE (user)-[:POSTED]->(entity) RETURN user")
    public Entity setPostedRelation(@Param("emailId") String firstName, @Param("entityId") String entityId);

    //Creates Entity IS_A SubDomain relationship by taking entityId from Entity node and subDomainName from SubDomain node
    @Query("MATCH(entity:Entity) WITH entity MATCH (subDomain:SubDomain) WHERE entity.entityId={entityId} and subDomain.subDomainName={subDomainName} CREATE (entity)-[:IS_A]->(subDomain) RETURN entity")
    public Entity setIsARelation(@Param("entityId") String entityId, @Param("subDomainName") String subDomainName);

    //Deletes Entity POSTED_BY User relationship by taking entityId from Entity node and firstName from User node
    @Query("MATCH(user:User) WITH user MATCH(entity:Entity) WHERE user.emailId={emailId} and entity.entityId={entityId} MATCH (user)-[r:POSTED]->(entity) DETACH DELETE r,entity RETURN user")
    public Entity deletePostedRelation(@Param("emailId") String firstName, @Param("entityId") String entityId);
}