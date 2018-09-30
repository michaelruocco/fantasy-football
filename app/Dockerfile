FROM openjdk:8-jre-alpine

COPY build/libs/app-*.jar /opt/app.jar

CMD ["/usr/bin/java","-Djava.security.egd=file:/dev/./urandom", "-jar", "/opt/app.jar"]