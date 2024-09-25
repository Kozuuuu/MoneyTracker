# Step 1: Use an official Maven image to build the application
FROM maven:latest AS build

# Copy the Maven project files
COPY . .

RUN mvn clean install -Dmaven.test.skip=true -q

#OUTPUT=JAR

FROM eclipse-temurin:17

# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR file from the Maven build stage
COPY --from=build /target/demo-0.0.1-SNAPSHOT.jar /app/myapp.jar

# Expose the port your application runs on
EXPOSE 8888

CMD ["java", "-jar", "/app/myapp.jar"]