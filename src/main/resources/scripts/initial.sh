#!/bin/bash

  # Create a directory for the k-curry-jib project
  echo "Creating and changing directory for the k-curry-jib project..."
  mkdir k-curry-jib
  cd k-curry-jib

  # Download initialization files and Dockerfile from the GitHub repository
  echo "Downloading initialization files and Dockerfile from GitHub..."
  curl -O https://raw.githubusercontent.com/ikar-zindo/k-curry-jib/main/src/main/resources/db/init-db.sql
  curl -O https://raw.githubusercontent.com/ikar-zindo/k-curry-jib/main/src/main/resources/db/Dockerfile

  echo "Downloading scripts..."
  curl -O https://raw.githubusercontent.com/ikar-zindo/k-curry-jib/main/src/main/resources/scripts/start.sh
  curl -O https://raw.githubusercontent.com/ikar-zindo/k-curry-jib/main/src/main/resources/scripts/stop.sh

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

  # Pull application Docker images from Docker Hub
  docker pull ikarzindo/k-curry-jib-employee-app
  docker pull ikarzindo/k-curry-jib-customer-app

  # Waiting for tables to initialize
  echo "Waiting for tables to initialize..."

  # Run application containers
  docker run -d -p 8888:8888 --link k-curry-jib-db:db --name k-curry-jib-employee --restart always ikarzindo/k-curry-jib-employee-app
  echo "Employee application is running  -  localhost:8888"

  docker run -d -p 8889:8889 --link k-curry-jib-db:db --name k-curry-jib-customer --restart always ikarzindo/k-curry-jib-customer-app
  echo "Customer application is running  -  localhost:8889"
