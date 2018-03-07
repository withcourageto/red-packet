FROM  java:8-jre


COPY ./target/demo-0.0.1-SNAPSHOT.jar  /app/redpacket.jar


CMD ["java", "agentlib:8005"  "-Xmx200m", "-jar", "/app/redpacket.jar"]

EXPOSE 8080