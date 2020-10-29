#!/bin/bash

. .env

VOLUME=demo-posrgres
if [ `docker volume ls |tr -s " " | cut -f2 -d" " | grep -e ^${VOLUME}$ | wc -l` -eq 0 ]; then
    docker volume create ${VOLUME}
fi

docker run --rm -it \
    --network demo-network \
    --expose 5432 \
    --env POSTGRES_DB=${DB_NAME}  \
    --env POSTGRES_USER=${DB_USERNAME} \
    --env POSTGRES_PASSWORD=${DB_PASSWORD} \
    --volume $VOLUME:/var/lib/postgresql/data \
    --label "traefik.enable=false" \
    --name "demo-postgres" \
    "postgres:12-alpine"
