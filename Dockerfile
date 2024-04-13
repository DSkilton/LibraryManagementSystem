FROM eclipse-temurin as builder

WORKDIR /workspace

COPY . .

FROM eclipse-temurin

# Sets the working directory for any RUN, CMD, ENTRYPOINT, COPY, and ADD instructions that follow
WORKDIR /app

COPY LibMan/build/libs/librarymanagement-0.0.1-SNAPSHOT.jar app.jar

RUN ls -la /app/*

VOLUME /tmp

EXPOSE 8080

ENTRYPOINT ["java","-Xmx1024m", "-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]