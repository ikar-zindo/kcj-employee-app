#!/bin/bash

	# Create a directory for the k-curry-jib project
	mkdir k-curry-jib

	# Change directory to the k-curry-jib project directory
	cd k-curry-jib

	# Download initialization files and Dockerfile from the GitHub repository
	curl -O k-curry-jib.sql https://raw.githubusercontent.com/ikar-zindo/k-curry-jib/main/k-curry-jib.sql
	curl -O Dockerfile https://raw.githubusercontent.com/ikar-zindo/k-curry-jib/main/src/main/resources/db/Dockerfile
	echo "Downloadied initialization files and Dockerfile from GitHub"

	# Build the Docker image for the database
	docker build -t k-curry-jib-db .

	# Run the database container
	docker run -d --name db k-curry-jib-db

	# Push application Docker images to Docker Hub
	docker push ikarzindo/k-curry-jib-employee-app
	docker push ikarzindo/k-curry-jib-customer-app

	# Run application containers
	docker run -d -p 8888:8888 --link db:db --name k-curry-jib-employee ikarzindo/k-curry-jib-employee-app
	echo "employee running"
	docker run -d -p 8889:8889 --link db:db --name k-curry-jib-customer ikarzindo/k-curry-jib-customer-app
	echo "customer running"