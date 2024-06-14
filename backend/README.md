# Gimme Quote Backend

This backend application serves quotes by fetching a quote from a random [external quote API](./src/main/java/com/zwanenberg/gimmequote/quote_sources/implementations) and mapping it to a [uniform quote response](./API.md). It is a [Spring Boot](https://spring.io/projects/spring-boot) project that was bootstrapped with [Spring Initializr](https://start.spring.io/).

## Installation

*See also: [Run app using Docker](#run-app-using-docker)*

Prerequisites:
- [JDK](https://openjdk.org/) version 17+ installed
- [Maven](https://maven.apache.org/) installed

From the project root:
1. `cd backend` to navigate into the backend directory
2. `mvn clean install -DskipTests` to clean and build project

## Commands

After [installation](#installation), the following commands are available from the `backend` directory:

- `mvn spring-boot:run` to run the application at [http://localhost:8080](http://localhost:8080)
- `mvn compile` to recompile
- `mvn test` run all tests

## Run app using Docker

Prerequisites:
- [Docker](https://www.docker.com/) installed and running
- Logged into [Docker Hub](https://hub.docker.com/)

From the project root:
1. `cd backend` to navigate into the frontend directory
2. `docker build -t backend .` to build image
3. `docker run -p 8080:8080 backend` to run the container

The application is now accessible through [http://localhost:8080](http://localhost:8080).

## API documentation
*See: [API documentation](./API.md)*
