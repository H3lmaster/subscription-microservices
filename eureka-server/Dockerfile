FROM java:8-jre

ADD target/eureka-server-0.0.1.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/eureka-server-0.0.1.jar"]

EXPOSE 8872