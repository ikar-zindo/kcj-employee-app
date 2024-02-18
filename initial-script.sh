#!/bin/bash

	# Create a directory for the k-curry-jib project
	echo "Creating directory for the k-curry-jib project..."
	mkdir k-curry-jib

	# Change directory to the k-curry-jib project directory
	echo "Changing directory to the k-curry-jib project directory..."
	cd k-curry-jib

	# Download initialization files and Dockerfile from the GitHub repository
	echo "Downloading initialization files and Dockerfile from GitHub..."
	curl -O https://raw.githubusercontent.com/ikar-zindo/k-curry-jib/main/src/main/resources/db/k-curry-jib.sql
  curl -O https://raw.githubusercontent.com/ikar-zindo/k-curry-jib/main/src/main/resources/db/Dockerfile

	# Build the Docker image for the database
	echo "Building the Docker image for the database..."
	docker build -t k-curry-jib-db .

	# Run the database container
	echo "Running the database container..."
	docker run -d --name k-curry-jib-db k-curry-jib-db

	# Wait for the database to be ready
  echo "Waiting for the database to start..."
  while ! docker exec k-curry-jib-db mysqladmin ping -hlocalhost --silent; do
      sleep 1
  done

  echo "Checking if the k-curry-jib table exists in the database..."
  sleep 15

  # Check if the k-curry-jib table exists in the database
#  echo "Waiting for the database to start..."
#  echo "12345" > password.txt

  # Ждем, пока таблица k-curry-jib не будет доступна в базе данных
#  while ! docker exec db bash -c 'mysql -uroot -p$(cat /password.txt) -e "USE k-curry-jib; SHOW TABLES LIKE \"k-curry-jib\";"' | grep -q "k-curry-jib"; do
#      echo "Waiting for the k-curry-jib table to be available in the database..."
#      sleep 1
#      docker cp password.txt db:/password.txt
#  done

  echo "Database is ready, proceeding with application startup."

	# Pull application Docker images to Docker Hub
	docker pull ikarzindo/k-curry-jib-employee-app
	docker pull ikarzindo/k-curry-jib-customer-app

	# Run application containers
	docker run -d -p 8888:8888 --link k-curry-jib-db:db --name k-curry-jib-employee ikarzindo/k-curry-jib-employee-app
	echo "Employee application is running  -  localhost:8888"

	docker run -d -p 8889:8889 --link k-curry-jib-db:db --name k-curry-jib-customer ikarzindo/k-curry-jib-customer-app
	echo "Customer application is running  -  localhost:8889"