FROM java:8
EXPOSE 8080
ADD /weather-app-0.0.1-SNAPSHOT.jar //
ENTRYPOINT ["java", "-jar", "/weather-app-0.0.1-SNAPSHOT.jar"]
