FROM maven:alpine AS builder
LABEL maintainer "David Perez Cabrera <dperezcabrera@gmail.com>"

WORKDIR /app/
COPY . .
RUN mvn package -Dmaven.test.skip=true
RUN mv target/*.jar target/spring-boot-app.jar

FROM openjdk:8-jre-alpine
EXPOSE 8080
COPY --from=builder /app/target/spring-boot-app.jar .
ADD config/docker/application.properties config/application.properties

ENTRYPOINT ["java", "-jar", "spring-boot-app.jar", "-Dspring.config.location=config/application.properties"]
