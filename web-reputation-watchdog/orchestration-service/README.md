ORCHESTRATION SERVICE:

            -Whenever a review is added from the front-end, it will be sent to the orchestration service backend.
            
            -After getting the details from the front-end orchestration-service will transfer the data from orchestration-service
                   to review-detector-service.
                   
            -If the review-detector-service detects the review  as fake it will change the geniune field to false.
            
            -Then the review is produced to rabbit-mq.
            
            -If the review-detector-service detects the review as true it will change the geniune field to true.
            
            -Then the review will be sent to rating-service for calculating rating.
            
            -After calculating rating orchestration-service will recieve that response for rating-service and 
                produce it into rabbit-mq producer.