FROM amazoncorretto:17.0.6-alpine3.17

COPY ./application/build/libs/application.jar /usr/local/sportsdata/application.jar

CMD ["java", \
        "-server", \
        "-jar", \
        "/usr/local/sportsdata/application.jar"]

EXPOSE 8080