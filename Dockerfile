FROM openjdk:9-jre
VOLUME /tmp
ARG JAR_FILE
ADD target/${JAR_FILE} /usr/share/lafeuille/ljcp-api.jar
ENTRYPOINT ["/usr/bin/java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/usr/share/lafeuille/ljcp-api.jar"]