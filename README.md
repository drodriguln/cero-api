# Cero API
Cero is a card game web application based on Crazy Eights. The UI and API for Cero is split between two code bases: `cero-ui` and `cero-api`.
This API, built on Spring Boot, is the interface between the Redis session store and the [cero-ui](https://github.com/drodriguln/cero-ui) frontend.

## Path to v1.0
This project is still a work in progress, and as such there are milestones yet to hit before hitting the first major release.

 - [x] works with cero-ui
 - [x] dockerize
 - [ ] swagger ui
 - [ ] validate card IDs
 - [ ] unit tests

## Getting Started

### Prerequisites
Most setups will require:
- `docker`
- `docker-compose`

A development setup can be run without docker using just:
 - `java 15`
 - `redis`

### Local Environment
To run this app locally:
1. Run `docker-compose up`
2. Follow the API docs from `http://localhost:8080/api-docs`.

### Development Environment
To set up an environment for development:
1. To run the application: `./mvnw spring-boot:run -Dspring-boot.run.profiles=dev`
2. Spin up a redis store. Either do this with a local redis install or use the redis docker image provided the dev docker-compose config by running `docker-compose -f docker-compose.dev.yaml up`

### Production Environment
Although a Cero production environment is actually created from the docker files in the Cero UI project as it contains the reverse proxy, an API-only deployment is possible using the same `docker-compose` config as the local environment setup:

So after connecting to your host server:
1. Copy over the `docker-compose.yaml` file from the project root onto your host server.
2. Run `docker-compose up`
3. Follow the API docs from `http://<your ip or host>:8080/api-docs`.
