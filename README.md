# Web application for food delivery K-Curry Jib

### Clone the repository

```
git clone git@github.com:ikar-zindo/k-curry-jib.git
```

---

### Launch jar archive

###### You need to make sure that the DB exists and is connected correctly.

*At the root of the project*

```
java -jar k-curry-jib-v1.1.1.jar
```

---

### [The docker way](https://hub.docker.com/repository/docker/ikarzindo/k-curry-jib-employee-app/general)

###### At the 1st launch, a DB will be created.

*At the root of the project*

```
docker-compose build
docker-compose up
```

---

### [View](http://localhost:8888/login)

*Copy to browser address bar*

```
http://localhost:8888/login
```

pass for all: `qwerty123`

- admin: `adam`, `anne`, `admin`
- manager: `manager`, `robert`
- user: `ewa`, `steven`, `margaret`, `janet`, `nancy` 

---

## Employee part web application

### Used technology stack in my web application:

- Spring Framework
- Spring Security
- MySQL
- JPA
- Model Mapper

---

- Jupiter - Testing
- Maven
- SLF4J

---

- WebMVC
- Thymeleaf
- Bootstrap
