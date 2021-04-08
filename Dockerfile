FROM adoptopenjdk/openjdk11:jre-11.0.6_10-alpine

WORKDIR /var/boozedb

ADD target/boozedb-1.0-SNAPSHOT.jar /var/boozedb/boozedb-1.0-SNAPSHOT.jar
COPY config.yml /var/boozedb/config.yml

EXPOSE 80

ENTRYPOINT ["java", "-jar", "boozedb-1.0-SNAPSHOT.jar", "server", "config.yml"]