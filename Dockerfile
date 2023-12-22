FROM eclipse-temurin:17-jdk-alpine
COPY target/k-curry-jib-0.0.1-SNAPSHOT.jar k-curry-jib.jar
COPY src/main/resources/templates/ /app/src/main/resources/templates/
ENTRYPOINT ["java","-Dspring.profiles.active=docker","-jar","/k-curry-jib.jar"]