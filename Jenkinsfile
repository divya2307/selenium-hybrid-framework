pipeline{
	agent any
	
	tools{
		maven 'Maven'
		jdk 'JDK'
	}
	
	parameters{
		choice(
			name: 'SUITE_XML',
			choices: ['testng-smoke.xml', 'testng-regression.xml', 'testng-cucumber.xml', 'testng-db.xml'],
			description : 'Select TestNG suite XML to execute'	
		)
		
		choice(
			name: 'ENV',
			choices: ['qa' , 'stage' , 'prod'],
			description: 'Select execution environment'
		)
		
		choice(
            name: 'BROWSER',
            choices: ['Chrome', 'Firefox', 'Edge'],
            description: 'Select browser'
        )

        booleanParam(
            name: 'HEADLESS',
            defaultValue: true,
            description: 'Run browser in headless mode'
        )

        string(
            name: 'APP_URL',
            defaultValue: '',
            description: 'Optional direct application URL override'
        )
        
        string(
            name: 'API_BASE_URI',
            defaultValue: '',
            description: 'Optional API base URI override'
        )
	}
	
	stages {
		stage('Checkout'){
			steps {
				checkout scm
			}
		}
		
		stage('Run Tests'){
			steps {
				script {
					def mvnCommand = "mvn clean test " +
                        "-DsuiteXmlFile=${params.SUITE_XML} " +
                        "-Denv=${params.ENV} " +
                        "-Dbrowser=${params.BROWSER} " +
                        "-Dheadless=${params.HEADLESS}"
                        
                    if (params.APP_URL?.trim()) {
                        mvnCommand += " -DappUrl=${params.APP_URL}"
                    }

                    if (params.API_BASE_URI?.trim()) {
                        mvnCommand += " -Dapi.baseUri=${params.API_BASE_URI}"
                    }

                    sh mvnCommand
				}
			}
		}
		
		post {
	        always {
	            archiveArtifacts artifacts: 'reports/**/*.html, logs/**/*.log, target/surefire-reports/**/*, target/cucumber-reports/**/*', allowEmptyArchive: true
	
	            junit allowEmptyResults: true, testResults: 'target/surefire-reports/junitreports/*.xml'
	        }
	
	        success {
	            echo 'Automation pipeline completed successfully.'
	        }
	
	        failure {
	            echo 'Automation pipeline failed. Check reports and logs.'
	        }
	}
	
}