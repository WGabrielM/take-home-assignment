FROM openjdk:17-alpine

WORKDIR /app

RUN apk update && apk add --no-cache \
    bash \
    coreutils \
    findutils \
    build-base

COPY build.gradle gradlew settings.gradle ./
COPY gradle/ gradle/

RUN chmod +x gradlew

RUN ./gradlew wrapper

COPY src/ src

ENTRYPOINT ["java", "-jar", "build/libs/sleep-0.0.1-SNAPSHOT.jar"]
