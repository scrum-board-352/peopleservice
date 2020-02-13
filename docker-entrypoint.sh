#!/usr/bin/env bash

set -e

if [ "$1" = "server" ]
then
    echo "Run app with app.env=${APP_ENV}"
	./gradlew --no-daemon bootRun -Dapp.env=${APP_ENV}
else
	eval "$@"
fi