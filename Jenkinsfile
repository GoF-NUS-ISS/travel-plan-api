pipeline{
    agent any
  
  stages{
    
    stage('API Maven build'){
      steps {
	sh (script: 'mvn -f microservicecloud/pom.xml clean install')
      }
    }

    stage('Run Docker Compose'){
      steps {
       script{

	try{

	  sh (script: 'sudo docker kill $(sudo docker ps -q)')
	  sh (script: 'sudo docker rm $(sudo docker ps -a -q)')
        } catch (err){
	  echo err.getMessage()
	}
        }
	 sh (script: 'sudo docker-compose -f microservicecloud/docker-compose.yml up -d')
      }
    }


    stage('Run API Automated Test'){
      steps {
       git 'https://github.com/GoF-NUS-ISS/travel-plan-qa.git'
       sh (script: 'mvn -f travel-plan-qa/pom.xml clean test')
      }
    }







  }
}
