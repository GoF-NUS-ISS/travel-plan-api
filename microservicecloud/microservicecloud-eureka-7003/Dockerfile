FROM java:8

VOLUME /tmp

ADD ./target/microservicecloud.jar /eureka3.jar

RUN bash -c 'touch /eureka3.jar'

EXPOSE 7003

ENTRYPOINT ["java", "-jar", "/eureka3.jar"]