FROM gradle:7.4.1 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build

FROM openjdk:21-jdk
WORKDIR /app

# FIXME
COPY --from=build /home/gradle/src/build/libs/couriers_tracking-0.0.1-SNAPSHOT.jar /app/app.jar

CMD ["java", "-jar", "app.jar"]
