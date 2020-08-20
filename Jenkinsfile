pipeline{
    agent any
  
  stages{

     stage('Branch') {
    	steps{
           echo "$GIT_BRANCH"
	}	
    }
    
    stage('API Maven build'){
      steps {
	sh (script: 'mvn -f microservicecloud/pom.xml clean install')
      }
    }

    stage('Run Docker Compose'){
      steps {
	sh (script: 'sudo docker-compose -f microservicecloud/docker-compose.yml up')
      }
    }
  }
}
