Micro-Service Name:- product-service

Description:
------------
This micro-service is responsible for saving an entity like movies, restaurants and so on if that particular entity is not present our database and at the same time produces saved entity data to the RabbitMQ. This micro-service contains the database mongoDB where all the entities are saved.
 Also, when a user adds a review to a particular entity, this micro-service is responsible for consuming the review data from RabbitMQ and updating the entity with the added reviews.