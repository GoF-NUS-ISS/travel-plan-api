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
	sh (script: 'mvn clean install')
      }
    }

    stage('Run Docker Compose'){
      steps {
	sh (script: 'sudo docker-compose up')
      }
    }
  }
}
