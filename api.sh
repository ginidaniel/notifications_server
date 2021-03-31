#!/usr/bin/env bash

if [[ $1 == *aws* ]]; then
    export JVM_OPTS='-Xmx512m -Xms512m'
#    export API_OPTS="-DCONFIG=config/application.json"
else
    export JVM_OPTS='-Xmx1g -Xms1g'
#    export API_OPTS="-DCONFIG=config/application-local.json"
fi

#export VERTX_OPTS='-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=8001 -Dvertx.metrics.options.enabled=false'

#export JAVA_OPTS="$JVM_OPTS $VERTX_OPTS $API_OPTS"
java $JVM_OPTS -jar pots-server-*.jar -cp .