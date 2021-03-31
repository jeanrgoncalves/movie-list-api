FROM maven:3.6.3-openjdk

COPY src home/movie-list-api/src
COPY pom.xml home/movie-list-api

WORKDIR home/movie-list-api
RUN mvn clean package

WORKDIR home/movie-list-api/target

EXPOSE 8443 5432

ENTRYPOINT ["java", "-jar", "movielist-0.0.1-SNAPSHOT.jar"]

