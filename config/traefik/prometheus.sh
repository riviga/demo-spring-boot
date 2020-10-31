#!/bin/bash


docker run --rm -it \
    --network demo-network \
    --expose 9090 \
    --volume "prometheus-discovery:/discovery/" \
    --volume "$PWD/prometheus.yml:/etc/prometheus/prometheus.yml" \
    --name prometheus \
    --label 'app=demo' \
    --label 'component=prometheus' \
    --label 'tier=monitoring' \
    --label 'traefik.enable=true' \
    --label 'traefik.http.routers.prometheus.rule=Host(`prometheus.demo-app.com`)' \
    --label 'traefik.http.routers.prometheus.tls=true' \
    --label 'traefik.http.middlewares.prometheus.redirectscheme.scheme=https' \
    --label 'traefik.http.middlewares.prometheus.redirectscheme.permanent=true' \
    prom/prometheus
