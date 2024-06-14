# Gimme Quote Frontend

## Introduction

This frontend web application makes requests to the [backend service](../backend) and displays the received quote to the user. It is a [Next.js](https://nextjs.org/) project bootstrapped with [create-next-app](https://github.com/vercel/next.js/tree/canary/packages/create-next-app).

## Installation

*See also: [Run app using Docker](#run-app-using-docker)*

Prerequisites:
- [Node.js](https://nodejs.org/) version 18+ installed
- [repository](..) cloned locally
- [`backend service`](../backend) running

From the project root:
1. `cd frontend` to navigate into the frontend directory
2. `npm install` to install dependencies:
3. `cp .env.example .env` to configure default environment variables
4. `vi .env` (or another editor) to modify environment variables *(optional)*

## Commands

After [installation](#installation), the following commands are available from the `frontend` directory:

- `npm run dev` to run development server, which will run the application at [http://localhost:3000](http://localhost:3000)
- `npm run test` to run tests
- `npm run test:watch` to run tests in watch mode
- `npm run lint` to run linter
- `npm run lint --fix` to run linter and automatically fix errors
- `npm run build` to create a production build

## Run app using Docker

Prerequisites:
- [Docker](https://www.docker.com/) installed and running
- Logged into [Docker Hub](https://hub.docker.com/)

From the project root:
1. `cd frontend` to navigate into the frontend directory
2. `docker build -t frontend .` to build image
3. `docker run -p 3000:3000 frontend` to run the container

The application is now accessible through [http://localhost:3000](http://localhost:3000).
