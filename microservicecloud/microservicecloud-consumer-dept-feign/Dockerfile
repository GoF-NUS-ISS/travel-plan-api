FROM java:8

VOLUME /tmp

ADD ./target/microservicecloud.jar /travel-api-consumer.jar

RUN bash -c 'touch /travel-api-consumer.jar'

EXPOSE 80

ENTRYPOINT ["java", "-jar", "/travel-api-consumer.jar"]