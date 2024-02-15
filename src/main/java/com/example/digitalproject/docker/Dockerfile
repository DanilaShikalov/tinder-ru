FROM eclipse-temurin:17-jdk-focal
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} tinder.jar
ENTRYPOINT ["java", "-jar", "tinder.jar"]