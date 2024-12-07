#!/usr/bin/env sh
docker buildx build --platform linux/amd64 -t app-petclinic-image:0.1-$(date +%s%3) -f images/Dockerfile .
/usr/local/bin/docker compose --platform linux/amd64 -f docker-compose.yml -p spring-petclinic up -d app


