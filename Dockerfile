# Use Java 21 base image
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app

# Copy Gradle build files
COPY build.gradle settings.gradle gradlew ./
COPY gradle ./gradle

# Download dependencies
RUN ./gradlew dependencies

# Copy source
COPY . .

# Build the app
RUN ./gradlew bootJar --no-daemon

# Run stage
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar

# Create a folder for H2 database file
RUN mkdir -p /data
VOLUME /data

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]