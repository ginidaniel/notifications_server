FROM 756427081490.dkr.ecr.eu-west-1.amazonaws.com/essearch/ess-jre:1.1
MAINTAINER pointsoftango@gmail.com "pointsoftango@gmail.com"

ENV NOTIFIER_HOME /opt/app/
RUN mkdir -p $NOTIFIER_HOME

RUN apk add --no-cache --update curl
RUN apk add

COPY docker/* $NOTIFIER_HOME

ENV TMP_OPTS -Djava.io.tmpdir=/tmp
ENV LOG4j_OPTS -Dlog4j.configurationFile=log4j2.xml
ENV NOTIFIER_OPTS $TMP_OPTS $LOG4j_OPTS

ENV JAVA_GC_OPTS -Xms1g -Xmx1g

WORKDIR $NOTIFIER_HOME
ENTRYPOINT ["sh", "-c", "/opt/app/entrypoint.sh"]