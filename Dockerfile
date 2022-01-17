FROM openjdk
VOLUME /redis-service
ADD target/rmitsb-0.0.1-SNAPSHOT.jar rmitsb-0.0.1-SNAPSHOT.jar
EXPOSE 9000
ENTRYPOINT ["java" , "-jar","rmitsb-0.0.1-SNAPSHOT.jar"]