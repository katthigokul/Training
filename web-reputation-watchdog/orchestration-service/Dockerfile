FROM openjdk:11

ADD target/orchestration-service-0.0.1-SNAPSHOT.jar /usr/src/orchestration-service-0.0.1-SNAPSHOT.jar
ADD ./src/main/resources/OrchestrationService.xlsx /usr/src/OrchestrationService.xlsx
WORKDIR usr/src

ENTRYPOINT ["java","-jar", "orchestration-service-0.0.1-SNAPSHOT.jar"]
