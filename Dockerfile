FROM maven:3.8.3-openjdk-17 AS build
ADD . /build
RUN cd /build && mvn package --quiet -DskipTests

FROM openjdk:17-alpine
COPY --from=build /build/target/*.jar /app.jar
ENTRYPOINT ["java", \
  "-jar", "/app.jar" \
  ]