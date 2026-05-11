# Stage 1: Build the application using Maven
FROM maven:3.8.7-eclipse-temurin-11 AS build
WORKDIR /app

# Copy the parent pom
COPY pom.xml .

# Copy all modules
COPY eureka-server/ ./eureka-server/
COPY zuul-gateway/ ./zuul-gateway/
COPY crew-service/ ./crew-service/
COPY aircraft-service/ ./aircraft-service/
COPY flight-service/ ./flight-service/

# Package all modules (skipping tests for faster build)
RUN mvn clean package -DskipTests

# Stage 2: Create the runtime image
FROM eclipse-temurin:11-jre-alpine
ARG MODULE_NAME
WORKDIR /app

# Copy the specific module's jar file built in Stage 1
COPY --from=build /app/${MODULE_NAME}/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
