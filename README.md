# Web API Validation for Java Project

### Requirements
- Java 8
- PostgreSQL

### Docker
If you familiar with docker you can setup the PostgreSQL Database using this command:
```
docker-compose up -d
```

### How to setup
rename file `src/main/resources/application.properties.example` to `src/main/resources/application.properties` and edit the file based on your local configuration.

### How to Run
```bash
./mvnw spring-boot:run
```
### How to Build
you can build the app using docker, the command is:
```bash
docker build -t [docker_username]/[docker_repository]:[version] .
```
if you aren't familiar with docker, you can build using this command:
```
mvn package
```

#### API Docs
you can import `Spring Starter.postman_collection.json` to your postman.