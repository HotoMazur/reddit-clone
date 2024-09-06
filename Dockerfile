FROM openjdk:21

COPY build/libs/reddit-0.0.1-SNAPSHOT.jar reddit-0.0.1-SNAPSHOT.jar

EXPOSE 8081

ENTRYPOINT ["java", "-Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}", "-jar", "/reddit-0.0.1-SNAPSHOT.jar"]