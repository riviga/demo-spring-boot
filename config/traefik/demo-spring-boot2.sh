#!/bin/bash

. .env

docker run --rm -it \
    --network demo-network \
    --expose 8080 \
    --env APP_URL="http://jenkins.cicd.com/" \
    --env DB_URL=${DB_URL}  \
    --env DB_USERNAME=${DB_USERNAME} \
    --env DB_PASSWORD=${DB_PASSWORD} \
    --env JWT_SECRET=${JWT_SECRET} \
    --env DB_DRIVER=org.hibernate.dialect.PostgreSQLDialect \
    --name demo-spring-boot2 \
    --label "traefik.enable=true" \
    --label "traefik.http.routers.demo-spring-boot.rule=Host(\`jenkins.cicd.com\`)" \
    --label "traefik.http.services.demo-spring-boot.loadbalancer.server.port=8080" \
    "dperezcabrera/demo-spring-boot:0.0.1"


#    --label "traefik.http.routers.demo-spring-boot.tls=true" \