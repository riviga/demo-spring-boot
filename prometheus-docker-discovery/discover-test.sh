#! /bin/bash

docker build . -t dperezcabrera/prometheus-docker-discovery

docker run --rm -it \
	--network demo-network \
	--volume "/var/run/docker.sock:/var/run/docker.sock:ro" \
	--volume "prometheus-discovery:/output" \
	--name prometheus-discovery \
	dperezcabrera/prometheus-docker-discovery
