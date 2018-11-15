FROM openjdk:11-jre
VOLUME /tmp
ARG JAR_FILE
ADD target/${JAR_FILE} /usr/share/lafeuille/ljcp-api.jar
EXPOSE 8080
ENTRYPOINT ["/usr/bin/java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/usr/share/lafeuille/ljcp-api.jar"]