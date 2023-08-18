FROM openjdk:17-jdk-alpine
EXPOSE 8080

# Copy the jar file into the image
ADD build/libs/messenger-0.0.1-SNAPSHOT.jar messenger-0.0.1-SNAPSHOT.jar

# Specify the command to run on boot
ENTRYPOINT ["java","-jar","messenger-0.0.1-SNAPSHOT.jar"]