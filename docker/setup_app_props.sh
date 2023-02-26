#!/usr/bin/env bash

pwd
ls -latrh
mkdir -p ./application/src/main/resources
echo

echo SPRING_DATA_MONGODB_DATABASE
echo "${SPRING_DATA_MONGODB_DATABASE}" | wc -c
echo SPRING_DATA_MONGODB_URI
echo "${SPRING_DATA_MONGODB_URI}" | wc -c

echo "logging.level.org.springframework.data.mongodb=DEBUG" >> ./application/src/main/resources/application.properties
echo "logging.level.sh.stefoosh.sportsdata.application=DEBUG" >> ./application/src/main/resources/application.properties
echo "logging.level.org.springframework.data.mongodb.repository.query=DEBUG" >> ./application/src/main/resources/application.properties
echo "${SPRING_DATA_MONGODB_DATABASE}" >> ./application/src/main/resources/application.properties
echo "${SPRING_DATA_MONGODB_URI}" >> ./application/src/main/resources/application.properties
ls -latrh ./application/src/main/resources/application.properties
cat ./application/src/main/resources/application.properties

echo
mkdir -p ./library/src/test/resources
echo "service.apiAuthHeaderKey=foo" >> ./library/src/test/resources/application.properties
echo "service.sportsDataApiBaseUrl=http://fakeApi" >> ./library/src/test/resources/application.properties
echo "service.mlbSubscriptionKey=asdf" >> ./library/src/test/resources/application.properties
echo "service.nhlSubscriptionKey=qwerty" >> ./library/src/test/resources/application.properties
echo "service.soccerSubscriptionKey=1234" >> ./library/src/test/resources/application.properties
echo "spring.data.mongodb.database=fakeDb" >> ./library/src/test/resources/application.properties
echo "spring.data.mongodb.uri=mongodb://fakeUri" >> ./library/src/test/resources/application.properties
ls -latrh ./library/src/test/resources/application.properties
cat ./library/src/test/resources/application.properties

echo
mkdir -p ./sync/src/main/resources
cp ./library/src/test/resources/application.properties ./sync/src/main/resources/application.properties
echo "spring.main.web-application-type=none" >> ./sync/src/main/resources/application.properties
ls -latrh ./sync/src/main/resources/application.properties
cat ./sync/src/main/resources/application.properties
echo

find . -name application.properties