FROM openjdk:15-slim
LABEL maintainer "David Perez Cabrera <dperezcabrera@gmail.com>"

WORKDIR /app

COPY target/*.jar demo-spring-boot.jar
ADD config/docker/application.yaml config/application.yaml

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "demo-spring-boot.jar", "-Dspring.config.location=config/application.yaml"]
