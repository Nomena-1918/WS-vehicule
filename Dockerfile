FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/WS-vehicule-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/app.jar"]
