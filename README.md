# travel-plan-api
For developing scaffold

1-Preparation:
Create 3 MySQL databases on your local machine With same instructions to create table, and then insert the data(referring to Appendix in the last part)
 
2-Description for different modules:

    a-Eureka cluster for microservice register:
        microservicecloud-eureka-7001
        microservicecloud-eureka-7002
        microservicecloud-eureka-7003
        
    b-microservice provider:
        microservicecloud-provider-dept-hystrix-8001 (use DB1, with service fussion function based on hystrix)
        microservicecloud-provider-dept-8002 (use DB2)
        microservicecloud-provider-dept-8003 (use DB3)
        
    c-interface of microservice consumed by internal application
         microservicecloud-api
         
    d-microservice consumer:
        microservicecloud-consumer-dept-feign
        (use feign to call microservices within a application, instead of httpclient, which are defined in the microservicecloud-api module)
        
    e-microservice provider performance metric monitor:
        microservicecloud-consumer-hystrix-dashboard
        
    f-microservice Gateway:
        microservicecloud-zuul-gateway-9527

3-Sequence of starting services:

    a -> b -> d -> e -> f 
    (skip c, which is integrated into specific module)

4-Two ways of starting services:

    1-run on the IDEA
        follow the sequence above, select each SpringBootApplication file, right click and choose "run as spring boot app"
        
    2-run on the local machine
        select the microservicecloud folder, choose "Run as maven clean" and "Run as maven install" 
        then find the jar file within each target folder of module
        open CMD console, and type "java -jar XXXX" , XXXX refers to the location of each jar
        (without using Tomcat/jetty container, which is already integrated into jar already)
 
 
 ====================Appendix===========================
 
      create database cloudDB01;
      create database cloudDB02;
      create database cloudDB03;

      CREATE TABLE Dept
      (
          deptno INT PRIMARY KEY,
          dname VARCHAR(50),
          db_source VARCHAR(50)
      );
  
      -- database 1
      INSERT INTO dept (deptno, dname, db_source) VALUES (1, 'Java1y', '1');
      INSERT INTO dept (deptno, dname, db_source) VALUES (2, 'Java2y', '1');
      INSERT INTO dept (deptno, dname, db_source) VALUES (3, 'Java3y', '1');
      INSERT INTO dept (deptno, dname, db_source) VALUES (4, 'Java4y', '1');
      INSERT INTO dept (deptno, dname, db_source) VALUES (5, 'Java5y', '1');

      -- database 2
      INSERT INTO dept (deptno, dname, db_source) VALUES (1, 'Java1y', '2');
      INSERT INTO dept (deptno, dname, db_source) VALUES (2, 'Java2y', '2');
      INSERT INTO dept (deptno, dname, db_source) VALUES (3, 'Java3y', '2');
      INSERT INTO dept (deptno, dname, db_source) VALUES (4, 'Java4y', '2');
      INSERT INTO dept (deptno, dname, db_source) VALUES (5, 'Java5y', '2');

      -- database 3
      INSERT INTO dept (deptno, dname, db_source) VALUES (1, 'Java1y', '3');
      INSERT INTO dept (deptno, dname, db_source) VALUES (2, 'Java2y', '3');
      INSERT INTO dept (deptno, dname, db_source) VALUES (3, 'Java3y', '3');
      INSERT INTO dept (deptno, dname, db_source) VALUES (4, 'Java4y', '3');
      INSERT INTO dept (deptno, dname, db_source) VALUES (5, 'Java5y', '3');
