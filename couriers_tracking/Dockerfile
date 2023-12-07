FROM openjdk:21-jdk AS build
WORKDIR /app
COPY . .

RUN ./gradlew build

FROM openjdk:21-jdk
WORKDIR /app

# FIXME
COPY --from=build /app/build/libs/couriers_tracking-0.0.1-SNAPSHOT.jar /app/app.jar

CMD ["java", "-jar", "app.jar"]
