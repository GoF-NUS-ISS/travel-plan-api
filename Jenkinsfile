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
	try{

	  sh (script: 'sudo docker kill $(sudo docker ps -q)')
	  sh (script: 'sudo docker rm $(sudo docker ps -a -q)')
        } catch (err){
	  echo err.getMessage()
	}
	 sh (script: 'sudo docker-compose -f microservicecloud/docker-compose.yml -d up')
      }
    }

    stage('Checkout QA FW'){
      steps {
	checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'dd6eabad-0b21-4f1b-9762-43fba555a0a6', url: 'https://github.com/GoF-NUS-ISS/travel-plan-qa.git']]])
      }
    }

    stage('Run API Automated Test'){
      steps {
	sh (script: 'mvn -f travel-plan-qa/pom.xml clean test')
      }
    }







  }
}
