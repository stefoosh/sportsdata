FROM amazoncorretto:17.0.6-alpine3.17

COPY ./application/build/libs/application.jar /usr/local/sportsdata/application.jar

CMD ["java", \
        "-server", \
        "-jar", \
        "/usr/local/sportsdata/application-0.0.1-SNAPSHOT.jar"]

EXPOSE 8080
