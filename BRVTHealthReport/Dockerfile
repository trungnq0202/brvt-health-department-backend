FROM openjdk
VOLUME /redis-service
ADD target/BRVT-HealthReport-Service.jar BRVT-HealthReport-Service.jar
EXPOSE 8087
ENTRYPOINT ["java" , "-jar","BRVT-HealthReport-Service.jar"]