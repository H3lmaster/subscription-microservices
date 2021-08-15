FROM java:8-jre

ADD target/zuul-server-0.0.1.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/zuul-server-0.0.1.jar"]

EXPOSE 8085