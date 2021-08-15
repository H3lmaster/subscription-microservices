#!/usr/bin/env bash

projectModules=( auth-server email-service eureka-server subscription-application subscription-service subscription-processor zuul-server )

for module in "${projectModules[@]}"; do
	docker build -t "microservices/${module}:latest" ${module}
done