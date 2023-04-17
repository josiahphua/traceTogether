FROM amazoncorretto:17
EXPOSE 8080
WORKDIR /app
COPY build/libs/demo-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
