#!/bin/bash

  # Stopping the database container
  echo "Stopping the database container..."
  docker stop k-curry-jib-db

  # Stopping the employee application container
  echo "Stopping the employee application container..."
  docker stop k-curry-jib-employee

  # Stopping the customer application container
  echo "Stopping the customer application container..."
  docker stop k-curry-jib-customer

  echo "All containers stopped."

  # Deleting file initial.sh
  cd ..
  if [ -f initial.sh ]; then
      rm initial.sh
      echo "File initial.sh has been deleted."
  else
      echo ""
  fi