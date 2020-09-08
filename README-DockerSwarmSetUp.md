# Docker Swarm
###Creating Machine Cluster and Swarm on AWS

1. Create AWS account
2. Create IAM account 
3. Get Access Id and secret id
4. Install AWS CLI on local.
6. Store Crendetials to .aws/credentails file
7. Run command to create ec2 instance: 
    1. “docker-machine create --driver amazonec2 --amazonec2-open-port 2377 aws-m1”
    2. “docker-machine create --driver amazonec2 --amazonec2-open-port 2377 aws-w1”
    3. “docker-machine create --driver amazonec2 --amazonec2-open-port 2377 aws-w2”
8. docker-machine env aws-m1
9. eval $(docker-machine env aws-m1)    ==> After this all the ‘docker’ command u will run will go to was-m1 machine
10. Docker swarm init (to init the swarm in m1 machine, you will get the command to join worker node to swarm)
11. Come out of m1 machine by running: eval $(docker-machine env -u)
12. Go to docker machine w1: eval $(docker-machine env aws-w1)
13. Run command on w1: docker swarm join --token SWMTKN-1-4dj2apdpr1sh5c20ne6drcier92334ytow63iv4n258nn6phy9-c3j4gdngwwt8fx7gdoj062sz7 172.31.80.91:2377
14. Similarly run the same command on w2: docker swarm join --token SWMTKN-1-4dj2apdpr1sh5c20ne6drcier92334ytow63iv4n258nn6phy9-c3j4gdngwwt8fx7gdoj062sz7 172.31.80.91:2377
15. Should get the message as: This node joined a swarm as a worker for w1 as well as w2 machine.
16. Come out of w1 and w2 machine: eval $(docker-machine env -u)
17. Reroute command back to docker leader machine: eval $(docker-machine env aws-m1)
18. Run: docker node ls  ==> you should get list of all the nodes in the swarm with leaders

####Creating services and replication on SWARM (above machine config can only hold 2 replications)
1. docker service create  --name=viz  --publish=8080:8080/tcp   --constraint=node.role==manager  --mount=type=bind,src=/var/run/docker.sock,dst=/var/run/docker.sock  dockersamples/visualizer
2. docker service create --name travel-plan-eureka --publish 7001:7001 gofnusiss/travel-plan-eureka-api:20200901-160308.cf94898
3. docker service create --name travel-plan-zuul --publish 9527:9527 gofnusiss/travel-plan-zuul-api:20200901-160308.cf94898
4. docker service create --name travel-plan-api --publish 8001:8001 gofnusiss/travel-plan-api:20200901-160308.cf94898
5. Access links:
    1. Visualization: 174.129.107.255:8080
    2. Zuul: 107.22.124.228:9527
    3. Eureka: 174.129.107.255:7001
    4. Travel Api: 18.212.38.100:8001
    
Note: If these ports are not accessible then go to aws and check the security group named as "docker-machine" and modify the inbound rules to provide access to these ports.