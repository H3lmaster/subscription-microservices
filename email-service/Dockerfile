FROM java:8-jre

ADD target/email-service-0.0.1.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/email-service-0.0.1.jar"]

EXPOSE 8090