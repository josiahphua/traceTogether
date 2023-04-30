# Build
FROM amazoncorretto:17.0.7-alpine3.17 AS builder

WORKDIR /app

COPY . /app

RUN ./gradlew clean bootJar --no-daemon

# Run
FROM amazoncorretto:17.0.7-alpine3.17

COPY --from=builder /app/build/libs/demo-0.0.1-SNAPSHOT.jar /app/app.jar
ADD entrypoint.sh /bin/entrypoint.sh
WORKDIR /app
EXPOSE 8081
ARG IMAGE_TAG
ENV IMAGE_TAG $IMAGE_TAG

CMD ["/bin/entrypoint.sh"]
