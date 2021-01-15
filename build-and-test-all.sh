#! /bin/bash

set -e

./gradlew $* testClasses

docker="./gradlew mysqlCompose"

${docker}Down -P removeContainers=true
${docker}Up

export EVENTUATEDATABASE=mysql

./gradlew cleanTest build

${docker}Down  -P removeContainers=true
