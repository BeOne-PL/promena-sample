#!/usr/bin/env bash
export BIN_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" >/dev/null 2>&1 && pwd)"
source $BIN_DIR/set-environment.sh

$BIN_DIR/stop.sh
docker volume rm ${PROJECT_NAME}_alfresco
docker volume rm ${PROJECT_NAME}_postgres
docker volume rm ${PROJECT_NAME}_solr6-data
docker volume rm ${PROJECT_NAME}_solr6-conf
docker volume rm ${PROJECT_NAME}_promena-communication
