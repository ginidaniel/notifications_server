#!/usr/bin/env sh

mo notifications-server.properties.tmpl > notifications-server.properties

echo "Launch the app: "

#launch the app
java $PUBLISHER_OPTS $JAVA_GC_OPTS -cp .:notifications-server-1.0.0.jar com.tesco.ess.publisher.PublisherRunnerQueue