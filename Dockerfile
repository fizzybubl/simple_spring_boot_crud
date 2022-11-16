FROM openjdk:17.0.2-slim-bullseye
ARG JAR_FILE=target/*.jar
COPY $JAR_FILE $PWD/spring-boot/app.jar
WORKDIR $PWD/spring-boot
ENTRYPOINT ["java", "-jar", "app.jar"]
