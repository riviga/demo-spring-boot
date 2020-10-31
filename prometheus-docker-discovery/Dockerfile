FROM alpine
LABEL maintainer "David Perez Cabrera <dperezcabrera@gmail.com>"

RUN apk --update add --no-cache docker
WORKDIR /script

ADD prometheus-docker-discovery.sh .
RUN chmod +x prometheus-docker-discovery.sh

# 10 seconds 
ENV INTERVAL_CHECK 10

ENV TARGETS_DIRECTORY /prometheus/discovery

ENTRYPOINT ["/script/prometheus-docker-discovery.sh"]
