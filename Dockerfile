FROM eclipse-temurin:20-jdk-alpine
VOLUME /tmp
COPY /target/*.jar executable.jar
ENTRYPOINT ["java","-jar","executable.jar"]
EXPOSE 8080