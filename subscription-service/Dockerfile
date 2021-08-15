FROM java:8-jre

ADD target/subscription-service-0.0.1.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/subscription-service-0.0.1.jar"]

EXPOSE 8084