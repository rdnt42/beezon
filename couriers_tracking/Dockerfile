FROM openjdk:21-jdk AS build
WORKDIR /app
COPY . .

RUN ./gradlew bootJar

FROM openjdk:21-jdk
WORKDIR /app

COPY --from=build /app/build/libs/couriers_tracking.jar /app/app.jar

CMD ["java", "-jar", "app.jar"]

