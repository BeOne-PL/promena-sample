#!/usr/bin/env bash
export BIN_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" >/dev/null 2>&1 && pwd)"
source $BIN_DIR/set-environment.sh

mvn clean package -f $BIN_DIR/../../../../image/promena-file-activemq-predefined-rendition/pom.xml && docker-compose -p $PROJECT_NAME -f $BIN_DIR/../docker-compose.yml build && docker-compose -p $PROJECT_NAME -f $BIN_DIR/../docker-compose.yml up -d
