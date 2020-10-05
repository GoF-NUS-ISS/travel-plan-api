# travel-plan-api

#### Other ReadMe Links:
* Set Up Docker Swarm: <a href='./README-DockerSwarmSetUp'>Docker Swarm Set Up on AWS</a>
***

For developing scaffold

NOTE: For microservicecloud-provider-dept-hystrix-8001 project, run 'docker-compse up -d' to bring up mongo instance. It will be accessible by localhost.

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

4-Three ways of starting services:

    1-run on the IDEA
        follow the sequence above, select each SpringBootApplication file, right click and choose "run as spring boot app"
        
    2-run on the local machine
        select the microservicecloud folder, choose "Run as maven clean" and "Run as maven install" 
        then find the jar file within each target folder of module
        open CMD console, and type "java -jar XXXX" , XXXX refers to the location of each jar
        (without using Tomcat/jetty container, which is already integrated into jar already)
        
    3-run in the docker
        Go into the folder "travel-plan-api\microservicecloud", zoom in CMD console, 
        step1：execute command "mvn clean package" -- generate the images and push them to dockerhub
        step2: execute command "docker-compose up -d" -- pull the images from dockerhub without build of container
        PS：
         docker stop $(docker ps -aq) --stop all existing containers
         docker rm $(docker ps -aq)  --remove all existing containers
         
         the account info in maven setting.xml like:
         
           <server>
               <id>registry-1.docker.io</id>
               <username> your account </username>
               <password> password or access token </password>
           </server>
             
 5-Open the API document
 
   http://localhost:8001/swagger-ui/index.html#/
   
   http://localhost:8002/swagger-ui/index.html#/
   
       localhost can be replaced with other actual IP address.
       
   http://localhost:9527/myPlan/swagger-ui/index.html#/
   
   http://localhost:9527/mySearch/swagger-ui/index.html#/
   
       via zuul to transmit
 
 ====================DEMO Appendix===========================
 
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
