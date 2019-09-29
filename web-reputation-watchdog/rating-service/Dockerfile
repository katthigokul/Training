#pull jdk image
FROM openjdk:11
#add/copy jar file from target to docker file system
ADD ./target/rating-service-0.0.1-SNAPSHOT.jar /usr/src/rating-service-0.0.1-SNAPSHOT.jar
ADD ./src/main/resources/tag-weightage.json /usr/src/tag-weightage.json
WORKDIR usr/src
#run the jar file
ENTRYPOINT ["java","-jar","rating-service-0.0.1-SNAPSHOT.jar"]
