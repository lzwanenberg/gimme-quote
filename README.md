# Gimme Quote

Gimme Quote is a service that provides the user with a quote that was sourced from a random external quote API.

## Global overview

The system contains of two components:

- [Backend](./backend): Java [Spring Boot](https://spring.io/projects/spring-boot) service:
  - Serves random quotes through a JSON API
  - Sources quotes from various external quote APIs
- [Frontend](./frontend): [React](https://react.dev/) / [Next.js](https://nextjs.org/) web application
  - Retrieves a random quote via the backend service
  - Shows quote to the user in a web UI
