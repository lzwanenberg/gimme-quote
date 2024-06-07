# BUILD
FROM cimg/openjdk:21.0.2 AS build
WORKDIR /app
COPY .mvn ./.mvn
COPY mvnw mvnw.cmd ./
COPY pom.xml .
COPY src ./src
USER root
RUN chmod +x mvnw
USER circleci
RUN ./mvnw clean package -DskipTests

# TEST
FROM build AS test
RUN ./mvnw test

# DEPLOY
FROM cimg/openjdk:21.0.2 AS deploy
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
