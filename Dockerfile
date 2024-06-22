FROM openjdk:8-alpine

WORKDIR /

ARG JAR_FILE_PATH=/lg-web/target/lg-web-0.0.1.jar
ARG DATASOURCE_KEY
ARG DATASOURCE_URL
ARG DATASOURCE_USERNAME
ARG DATASOURCE_PASSWORD

ENV ACTIVE_PROFILE="local" \
    DATASOURCE_KEY=${DATASOURCE_KEY} \
    DATASOURCE_URL=${DATASOURCE_URL} \
    DATASOURCE_USERNAME=${DATASOURCE_USERNAME} \
    DATASOURCE_PASSWORD=${DATASOURCE_PASSWORD}

COPY /${JAR_FILE_PATH} ROOT.jar

EXPOSE 19001

ENTRYPOINT ["java", "-jar", "-Xincgc", "-Xmx256m", "-Dspring.profiles.active=${ACTIVE_PROFILE}", "-Dencryptor.key=${DATASOURCE_KEY}", "-Dspring.datasource.url=${DATASOURCE_URL}", "-Dspring.datasource.username=${DATASOURCE_USERNAME}", "-Dspring.datasource.password=${DATASOURCE_PASSWORD}", "-Duser.timezone=Asia/Seoul", "-Dcom.sun.management.jmxremote", "-Dcom.sun.management.jmxremote.port=37010", "-Dcom.sun.management.jmxremote.rmi.port=37010", "-Djava.rmi.server.hostname=lawgg-spring", "-Dcom.sun.management.jmxremote.ssl=false", "-Dcom.sun.management.jmxremote.authenticate=false", "ROOT.jar"]

