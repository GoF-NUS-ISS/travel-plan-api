FROM java:8

VOLUME /tmp

ADD ./target/microservicecloud.jar ./travel_elasticsearch.jar

RUN bash -c 'touch ./travel_elasticsearch.jar'

EXPOSE 8002

ENTRYPOINT ["java", "-jar", "./travel_elasticsearch.jar"]