#! /bin/bash

docker run --rm -it \
    --network demo-network \
    --volume "/var/run/docker.sock:/var/run/docker.sock:ro" \
    --volume "prometheus-discovery:/prometheus/discovery" \
    --name prometheus-discovery \
    --label 'traefik.enable=false' \
    dperezcabrera/prometheus-docker-discovery
