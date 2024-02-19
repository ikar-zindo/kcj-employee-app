#!/bin/bash

  # Stopping the employee application container
  echo "Stopping the employee application container..."
  docker stop k-curry-jib-employee

  # Stopping the customer application container
  echo "Stopping the customer application container..."
  docker stop k-curry-jib-customer

  # Stopping the database container
  echo "Stopping the database container..."
  docker stop k-curry-jib-db

  echo "All containers stopped."