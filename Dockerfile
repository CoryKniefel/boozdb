FROM adoptopenjdk/openjdk11:jre-11.0.6_10-alpine

WORKDIR /var/boozedb

ADD target/boozedb-1.0-SNAPSHOT.jar /var/boozedb/boozedb-1.0-SNAPSHOT.jar
ADD config.yml /var/boozedb/config.yml

EXPOSE 8080 3306

ENTRYPOINT ["java", "-jar", "boozedb-1.0-SNAPSHOT.jar", "server", "config.yml"]