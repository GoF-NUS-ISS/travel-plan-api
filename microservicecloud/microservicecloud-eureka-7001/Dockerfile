FROM java:8

VOLUME /tmp

ADD ./target/microservicecloud.jar /eureka1.jar

RUN bash -c 'touch /eureka1.jar'

EXPOSE 7001

ENTRYPOINT ["java", "-jar", "/eureka1.jar"]