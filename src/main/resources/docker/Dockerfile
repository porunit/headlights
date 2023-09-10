FROM openjdk:17-jdk
VOLUME /tmp
COPY target/headlight-website-0.0.1-SNAPSHOT.jar /app.jar
COPY *.sql /docker-entrypoint-initdb.d/
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]


