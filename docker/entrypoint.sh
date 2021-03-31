#!/usr/bin/env sh

mo notifications_server.properties.tmpl > notifications_server.properties

echo "Starting the process: "

#launch the process
java $NOTIFIER_OPTS $JAVA_GC_OPTS -cp .:notifications_server-1.0.0.jar com.inspiring.solutions.notifications.server.NotificationProcess