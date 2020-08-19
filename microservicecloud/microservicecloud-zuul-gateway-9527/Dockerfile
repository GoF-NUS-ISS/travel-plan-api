FROM java:8

VOLUME /tmp

ADD ./target/microservicecloud.jar /zuul.jar

RUN bash -c 'touch /zuul.jar'

EXPOSE 9527

ENTRYPOINT ["java", "-jar", "/zuul.jar"]