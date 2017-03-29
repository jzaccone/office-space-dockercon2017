#!/bin/bash

docker ps -a | tail -4 | awk '{print $1}' | xargs docker rm -f 
cd compute-interest-api
mvn clean install
cd ..
docker-compose build
docker-compose up
