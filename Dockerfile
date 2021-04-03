FROM openjdk:8
MAINTAINER pointsoftango@gmail.com "pointsoftango@gmail.com"

ENV NOTIFIER_HOME /opt/app/
ENV NOTIFIER_LOGS /opt/notifications/logs/
RUN mkdir -p $NOTIFIER_HOME
RUN mkdir -p $NOTIFIER_LOGS

RUN apt update
#RUN apt install curl


COPY docker/* $NOTIFIER_HOME

ENV TMP_OPTS -Djava.io.tmpdir=/tmp
ENV LOG4j_OPTS -Dlog4j.configurationFile=log4j2.xml
ENV NOTIFIER_OPTS $TMP_OPTS $LOG4j_OPTS

ENV JAVA_GC_OPTS -Xms1g -Xmx1g

WORKDIR $NOTIFIER_HOME
ENTRYPOINT ["sh", "-c", "/opt/app/entrypoint.sh"]