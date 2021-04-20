FROM maven:3.6.3-openjdk-11

COPY src home/movie-list-api/src
COPY pom.xml home/movie-list-api

WORKDIR /home/movie-list-api
RUN mvn clean package

ENTRYPOINT ["java", "-jar", "/home/movie-list-api/target/movielist-0.0.1-SNAPSHOT.jar"]