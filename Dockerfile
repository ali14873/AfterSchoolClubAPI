# Use the official Maven image to create a build artifact.
# This image includes the JDK and Maven.
FROM maven:3.8-openjdk-17 as builder

# Copy the pom.xml and source code
COPY ./pom.xml ./pom.xml
COPY ./src ./src

# Package the application
RUN mvn clean package

# Use the official Tomcat image for the runtime
FROM tomcat:9.0-jre17

# Remove the default web applications deployed in Tomcat
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy the WAR file from the build stage to the Tomcat webapps directory
COPY --from=builder /target/userRegistration-0.0.1-SNAPSHOT.jar /usr/local/tomcat/webapps/ROOT.jar

# Expose the port the application runs on
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "/usr/local/tomcat/webapps/ROOT.jar"]

# Tomcat's default startup command will be used, which starts Tomcat and deploys the WAR
# No CMD instruction is necessary because the base image already defines the startup command for Tomcat
