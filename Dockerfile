FROM openjdk:17-jdk-slim
ARG JAR_FILE=WS-vehicule-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
