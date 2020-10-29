#!/bin/bash

docker run --rm -it \
    --network demo-network \
    --publish 80:80 \
    --publish 443:443 \
    --volume "/var/run/docker.sock:/var/run/docker.sock:ro" \
    --name traefik  \
    --label "traefik.enable=true" \
    --label "traefik.http.routers.traefik.rule=Host(\`traefik.cicd.com\`)" \
    --label "traefik.http.services.traefik.loadbalancer.server.port=8080" \
    traefik --entrypoints.web.address=:80 --providers.docker --entrypoints.web-secure.address=:443 --providers.file.watch=true --providers.file.directory=/configuration/
