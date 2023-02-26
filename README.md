# sportsdata

Based on:

- Gradle 7.5
- OpenJDK 17
- Spring Boot 3
- Spring Data MongoDB 4
- MongoDB 6

Featuring:
- Backend client service calls with Spring Framework 5 WebClient
- Testcontainers beneath controller unit tests

#
Run unit and integration tests on all subprojects
```shell
./gradlew clean test
```
Import sportsdata into MongoDB
```shell
./gradlew :sync:bootRun --args="yip1 ee2"
```
Start the web application
```shell
./gradlew :application:bootRun
```
Build and run web app jar
```shell
./gradlew clean bootJar && java -server -jar ./application/build/libs/application.jar
```
Build Docker images
```shell
./gradlew clean test build bootJar
DOCKER_BUILDKIT=0 docker build . -f docker/Dockerfile --platform linux/x86_64/v8
```
Running the prod image
```shell
docker run -p 8080:8080 --platform linux/amd64 stefoosh/sportsdata:latest
```

Secrets and constants go into `src/{main,test}/resources/application.properties` on a per-subproject basis.
They're defined in `.gitignore` and not included in this repo.
