#!/usr/bin/env bash

pwd
ls -latrh
mkdir -p ./application/src/main/resources
mkdir -p ./sync/src/main/resources
echo

APP_PROPS="./application/src/main/resources/application.properties"
SYNC_PROP="./sync/src/main/resources/application.properties"

cp ./docker/application-properties "${APP_PROPS}"

echo "spring.main.web-application-type=none" >> "${SYNC_PROP}"

echo
ls -latrh "${APP_PROPS}"
cat "${APP_PROPS}"
echo
ls -latrh "${SYNC_PROP}"
cat "${SYNC_PROP}"
