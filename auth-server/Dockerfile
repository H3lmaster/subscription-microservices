FROM java:8-jre

ADD target/auth-server-0.0.1.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/auth-server-0.0.1.jar"]

EXPOSE 8096