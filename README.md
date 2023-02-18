# sportsdata

Based on:

- Gradle 7.5
- OpenJDK 17
- Spring Boot 3

Featuring:
- Backend client service calls with Spring 5 WebClient

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

Secrets and constants go into `resources/application.properties` on a per-subproject basis.
They're defined in `.gitignore` and not included in this repo.
