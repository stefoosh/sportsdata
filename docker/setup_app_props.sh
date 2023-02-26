#!/usr/bin/env bash

java -version

pwd
ls -latrh
mkdir -p ./application/src/main/resources
mkdir -p ./sync/src/main/resources
echo

echo SPRING_DATA_MONGODB_DATABASE
echo "${SPRING_DATA_MONGODB_DATABASE}" | wc -c
echo "${SPRING_DATA_MONGODB_DATABASE}" | base64
echo SPRING_DATA_MONGODB_URI
echo "${SPRING_DATA_MONGODB_URI}" | wc -c
echo "${SPRING_DATA_MONGODB_URI}" | base64

APP_PROPS="./application/src/main/resources/application.properties"
SYNC_PROP="./sync/src/main/resources/application.properties"

cp ./docker/application-properties "${APP_PROPS}"
echo "spring.data.mongodb.database=${SPRING_DATA_MONGODB_DATABASE}" >> "${APP_PROPS}"
echo "spring.data.mongodb.uri=${SPRING_DATA_MONGODB_URI}" >> "${APP_PROPS}"

echo "spring.main.web-application-type=none" >> "${SYNC_PROP}"

echo
ls -latrh "${APP_PROPS}"
cat "${APP_PROPS}"
ls -latrh "${SYNC_PROP}"
cat "${SYNC_PROP}"
