# Web application for food delivery K-Curry Jib

## The application manages orders:

#### <ROLE_ADMIN>

- adding products (expanding the range)
- changing product information
- blocking products (put the position on stop)
- adding restaurants
- changing information about the restaurant
- opening/closing of the restaurant
- adding new employees (only <ROLE_USER>)
- changing product information
- monitoring of all orders
- changing product information

#### <ROLE_USER>

- order status management
- monitoring order history
- blocking products (put the position on STOP)


### 1. Clone the repository

```
git clone git@github.com:ikar-zindo/kcj-employee-app.git
```

---

### 2. Launch jar archive

> [!IMPORTANT]
> The driver for the *MySQL* database must be installed on the computer.
> For example *Workbench*. Availability of a created database `kcj-db`.
> *Liquibase* will create all the necessary tables for the application to work properly.
> It is necessary to specify these environment variables to connect to the database

- DATASOURCE_DATABASE_HOST=<YOUR_HOST>
- DATASOURCE_DATABASE_PORT=<YOUR_PORT>
- DATASOURCE_DATABASE_NAME=<YOUR_NAME>
- DATASOURCE_DATABASE_USERNAME=<YOUR_USERNAME>
- DATASOURCE_DATABASE_PASSWORD=<YOUR_PASSWORD>


*At the root of the project*

```
java -jar kcj-employee-app.jar
```

---

### [3. The docker way](https://hub.docker.com/repository/docker/ikarzindo/k-curry-jib-employee-app/general)

###### At the 1st launch, a DB will be created.

*At the root of the project*

```bash
# Collect images for all services
docker-compose -p kcj build

# Start all services
docker-compose -p kcj up -d

# Check the status of running containers
docker-compose -p kcj ps

# View logs (optional)
docker-compose -p kcj logs -f

# Stop all services
docker-compose -p kcj stop

# Start all services
docker-compose -p kcj start

# Deactivate all services (if necessary)
docker-compose -p kcj down
```

---

### [4. View](http://localhost:8880/login)

*Copy to browser address bar*

```
http://localhost:8880/login
```

pass for all: `qwerty123`

- admin: `adam`, `anne`, `admin`
- manager: `manager`, `robert`
- user: `ewa`, `steven`, `margaret`, `janet`, `nancy`

---

### 5. Used technology stack in my web application:

- Spring Framework
- Spring Security
- MySQL
- JPA
- Model Mapper
- Liquibase

---

- Jupiter - Testing
- Maven
- SLF4J

---

- WebMVC
- Thymeleaf
- Bootstrap
