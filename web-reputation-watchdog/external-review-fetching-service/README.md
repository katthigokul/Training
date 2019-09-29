**Description**:
 
 This micro service is responsible for fetching data from external websites for the entities which are not
 available in our platform so that it can be reviewed by the users. The External Sites used in the service are 
 Zomato API and OMDB API. The data should be produced to Notification Service via RabbitMQ whenever a user searches for
 a particular entity.
 
**User Stories:** 
    1. The system should provide external rating and reviews for restaurant if not present in the system
    2. The system should provide external rating and reviews for movies if not present in the system
    
**Json Data Format:**
    [
      {
        "entityName": "Psycho",
        "photoUrl": "https://m.media-amazon.com/images/M/MV5BNTQwNDM1YzItNDAxZC00NWY2LTk0M2UtNDIwNWI5OGUyNWUxXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_SX300.jpg",
        "rating": "8.5",
        "description": "A Phoenix secretary embezzles forty thousand dollars from her employer's client, goes on the run, and checks into a remote motel run by a young man under the domination of his mother.",
        "highlights": null,
        "address": null,
        "locality": null,
        "city": null,
        "email": naveen@gmail.com
      }
    ]