FROM openjdk:11

ADD target/external-review-fetching-service-0.0.1-SNAPSHOT.jar /usr/src/external-review-fetching-service-0.0.1-SNAPSHOT.jar

WORKDIR usr/src

ENTRYPOINT ["java","-jar", "external-review-fetching-service-0.0.1-SNAPSHOT.jar"]