#!/bin/bash

  # Run the database container
  echo "Running the database container..."
  docker start k-curry-jib-db

  # Connecting to the database
  echo "Connecting to the database..."
  while ! docker exec k-curry-jib-db mysqladmin ping -hlocalhost --silent; do
      sleep 1
  done

  # Starting the application
  docker start k-curry-jib-employee
  echo "Employee application is running  -  localhost:8888"

  docker start k-curry-jib-customer
  echo "Customer application is running  -  localhost:8889"