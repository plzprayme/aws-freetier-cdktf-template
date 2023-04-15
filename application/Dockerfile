FROM eclipse-temurin:17.0.6_10-jre

WORKDIR /home
COPY ./build/libs/demo-0.0.1-SNAPSHOT.jar /home/application.jar

ENTRYPOINT ["java", "-jar", "/home/application.jar"]
