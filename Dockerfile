# Use an official OpenJDK runtime as a parent image
FROM openjdk:22-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the executable JAR file to the container
COPY target/jobysnc-api-0.0.1-SNAPSHOT.jar /app/jobsync-api.jar

# Expose the port that the application will run on
EXPOSE 8091

# Run the JAR file
ENTRYPOINT ["java", "-jar", "service1.jar"]