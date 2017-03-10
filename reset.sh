#!/bin/bash

docker ps -a | tail -3 | awk '{print $1}' | xargs docker rm -f 
cd app
mvn clean install
cd ..
docker-compose build
docker-compose up
