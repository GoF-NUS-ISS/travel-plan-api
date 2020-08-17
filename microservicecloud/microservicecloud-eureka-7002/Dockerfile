FROM java:8

VOLUME /tmp

ADD ./target/microservicecloud.jar /eureka2.jar

RUN bash -c 'touch /eureka2.jar'

EXPOSE 7002

ENTRYPOINT ["java", "-jar", "/eureka2.jar"]