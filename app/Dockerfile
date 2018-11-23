FROM openjdk:8-jre-alpine

USER 10001

COPY build/libs/app-*.jar /opt/app.jar
EXPOSE 8080

CMD ["/usr/bin/java","-Djava.security.egd=file:/dev/./urandom","-Xmx256m", "-jar", "/opt/app.jar"]