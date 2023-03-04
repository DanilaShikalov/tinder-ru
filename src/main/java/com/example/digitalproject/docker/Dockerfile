FROM eclipse-temurin:17-jdk-focal
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} digital.jar
ENTRYPOINT ["java", "-jar", "digital.jar"]