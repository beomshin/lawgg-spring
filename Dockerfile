FROM openjdk:8-alpine

WORKDIR /

ARG JAR_FILE_PATH=/target/lawgg-spring-0.0.1.jar

ENV ACTIVE_PROFILE="local"

COPY /${JAR_FILE_PATH} ROOT.jar

EXPOSE 19001

ENTRYPOINT ["java", "-jar", "-Xincgc", "-Xmx256m", "-Dspring.profiles.active=${ACTIVE_PROFILE}", "-Dencryptor.key=${DATASOURCE_KEY}", "-Duser.timezone=Asia/Seoul", "-Dcom.sun.management.jmxremote", "-Dcom.sun.management.jmxremote.port=37010", "-Dcom.sun.management.jmxremote.rmi.port=37010", "-Djava.rmi.server.hostname=lawgg-spring", "-Dcom.sun.management.jmxremote.ssl=false", "-Dcom.sun.management.jmxremote.authenticate=false", "ROOT.jar"]

