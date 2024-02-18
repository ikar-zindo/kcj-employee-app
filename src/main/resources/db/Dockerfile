FROM mysql:latest

ENV MYSQL_DATABASE=k-curry-jib
ENV MYSQL_ROOT_PASSWORD=12345

COPY k-curry-jib.sql /docker-entrypoint-initdb.d/

EXPOSE 3306