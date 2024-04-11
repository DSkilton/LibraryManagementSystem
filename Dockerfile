FROM openjdk:11-alpine

# Sets the working directory for any RUN, CMD, ENTRYPOINT, COPY, and ADD instructions that follow
WORKDIR /app

COPY build/libs/library-management-system-0.1.0.jar app.jar
# Copies your built jar file into the image as "app.jar"

VOLUME /tmp

EXPOSE 8080

# Sets the default command to run when the container starts which is to run the jar file
ENTRYPOINT ["java","-Xmx1024m", "-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]