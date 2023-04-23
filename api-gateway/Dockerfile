FROM eclipse-temurin:17_35-jdk-alpine
COPY /target/*.jar /api-gateway.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/api-gateway.jar"]