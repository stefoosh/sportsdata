#!/usr/bin/env bash

java -version

pwd
ls -latrh
mkdir -p ./application/src/main/resources
mkdir -p ./library/src/test/resources
mkdir -p ./sync/src/main/resources
echo

echo SPRING_DATA_MONGODB_DATABASE
echo "${SPRING_DATA_MONGODB_DATABASE}" | wc -c
echo "${SPRING_DATA_MONGODB_DATABASE}" | base64
echo SPRING_DATA_MONGODB_URI
echo "${SPRING_DATA_MONGODB_URI}" | wc -c
echo "${SPRING_DATA_MONGODB_URI}" | base64

APP_PROPS="./application/src/main/resources/application.properties"
LIB_PROPS="./library/src/test/resources/application.properties"
SYNC_PROP="./sync/src/main/resources/application.properties"

echo "org.testcontainers=INFO" >> "${APP_PROPS}"
echo "com.github.dockerjava=WARN" >> "${APP_PROPS}"
echo "com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire=OFF" >> "${APP_PROPS}"
cp "${APP_PROPS}" "${LIB_PROPS}"

echo
echo "logging.level.org.springframework.data.mongodb=DEBUG" >> "${APP_PROPS}"
echo "logging.level.sh.stefoosh.sportsdata.application=DEBUG" >> "${APP_PROPS}"
echo "logging.level.org.springframework.data.mongodb.repository.query=DEBUG" >> "${APP_PROPS}"
echo "${SPRING_DATA_MONGODB_DATABASE}" >> "${APP_PROPS}"
echo "${SPRING_DATA_MONGODB_URI}" >> "${APP_PROPS}"

echo
echo "service.apiAuthHeaderKey=foo" >> "${LIB_PROPS}"
echo "service.sportsDataApiBaseUrl=http://fakeApi" >> "${LIB_PROPS}"
echo "service.mlbSubscriptionKey=asdf" >> "${LIB_PROPS}"
echo "service.nhlSubscriptionKey=qwerty" >> "${LIB_PROPS}"
echo "service.soccerSubscriptionKey=1234" >> "${LIB_PROPS}"
#echo "spring.data.mongodb.database=fakeDb" >> "${LIB_PROPS}"
#echo "spring.data.mongodb.uri=mongodb://fakeUri" >> "${LIB_PROPS}"

echo
cp "${LIB_PROPS}" "${SYNC_PROP}"
echo "spring.main.web-application-type=none" >> "${SYNC_PROP}"

echo
ls -latrh "${APP_PROPS}"
cat "${APP_PROPS}"
ls -latrh "${LIB_PROPS}"
cat "${LIB_PROPS}"
ls -latrh "${SYNC_PROP}"
cat "${SYNC_PROP}"

echo
find . -name application.properties