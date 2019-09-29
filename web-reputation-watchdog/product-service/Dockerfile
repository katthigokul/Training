FROM openjdk:11

ADD ./target/product-service-0.0.1-SNAPSHOT.jar /usr/src/product-service-0.0.1-SNAPSHOT.jar
ADD ./src/main/resources/Product-Service.xlsx /usr/src/Product-Service.xlsx
WORKDIR usr/src

ENTRYPOINT ["java","-jar", "product-service-0.0.1-SNAPSHOT.jar"]
