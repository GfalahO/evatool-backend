#!/bin/bash
echo ------------------------------
echo DOCKER BUILD
docker build -t evatoolapp_image /home/evauser/evatool
echo ------------------------------
echo RUNNING CONTAINERS
docker ps
echo ------------------------------
echo STOP DOCKER IMAGE
docker stop evatoolapp_container
echo ------------------------------
echo START DOCKER IMAGE
docker run --name evatoolapp_container -d --rm -p 443:8080 evatoolapp_image --server.port=8080
echo ------------------------------
echo RUNNING CONTAINERS
docker ps
echo ------------------------------
echo ALL DOCKER IMAGES
docker images
echo ------------------------------
echo REMOVE DANGLING IMAGES
docker images --no-trunc -q -f dangling=true | xargs -r docker rmi
echo ------------------------------
echo ALL DOCKER IMAGES
docker images
echo ------------------------------

