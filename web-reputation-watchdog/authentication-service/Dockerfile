FROM openjdk:11

ADD ./target/jwt-authentication-0.0.1-SNAPSHOT.jar /usr/src/jwt-authentication-0.0.1-SNAPSHOT.jar

WORKDIR usr/src

ENTRYPOINT ["java","-jar", "jwt-authentication-0.0.1-SNAPSHOT.jar"]
