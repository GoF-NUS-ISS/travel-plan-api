FROM java:8

VOLUME /tmp

ADD ./target/microservicecloud.jar ./travel_api.jar

RUN bash -c 'touch ./travel_api.jar'

EXPOSE 8001

ENTRYPOINT ["java", "-jar", "./travel_api.jar"]