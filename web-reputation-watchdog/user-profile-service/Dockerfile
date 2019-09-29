FROM openjdk:11
ADD ./target/user-profile-service-0.0.1-SNAPSHOT.jar /usr/src/user-profile-service-0.0.1-SNAPSHOT.jar
ADD ./src/main/resources/UserProfile_2.xlsx /usr/src/UserProfile_2.xlsx
WORKDIR usr/src
ENTRYPOINT ["java","-jar","user-profile-service-0.0.1-SNAPSHOT.jar"]

