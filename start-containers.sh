#!/bin/bash

DOCKER_COMPOSE_FILE="docker-compose.yml"
sudo docker compose -f $DOCKER_COMPOSE_FILE build
sudo docker compose -f $DOCKER_COMPOSE_FILE up -d

cd front
ng serve 
