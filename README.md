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

Component diagram:

![Component diagram](./documentation/component_diagram.svg)

## Installation

**Option 1: use Docker Compose**

Use [Docker Compose](https://docs.docker.com/compose/) for a quick preview or simple deployments:
- `docker compose up --build`:
  - serves web UI on port 3000
  - serves quote API on port 8080

**Option 2: run components individually**

Run components individually for active development or advanced deployments:

*See: [backend/README.md](./backend/README.md) and [frontend/README.md](./frontend/README.md)*
