pipeline {
    agent any

    tools {
        jdk 'jdk17'        // JDK you configured in Jenkins
        maven 'Maven3'     // Maven you configured in Jenkins
    }

    stages {
        stage('Checkout') {
            steps {
                echo "Cloning GitHub repository..."
                git url: 'https://github.com/mrinalproxy/Mrinal-Kanti-Ghosh-Wipro-Capstone-Project.git', branch: 'main'
            }
        }

        stage('Clean and Build') {
            steps {
                echo "Cleaning and building project using Maven..."
               // sh "mvn clean compile"  // on Linux/Mac
                 bat "mvn clean compile" // on Windows
            }
        }

        stage('Run Tests') {
            steps {
                echo "Running TestNG+Cucumber tests..."
                //sh "mvn test"  // on Linux/Mac
                 bat "mvn test" // on Windows
            }
        }

        stage('Generate Report') {
            steps {
                echo "Generating Cucumber HTML report..."
                cucumber buildStatus: 'UNSTABLE', fileIncludePattern: '**/cucumber.json', jsonReportDirectory: 'target/cucumber-reports', pluginUrlPath: '', sortingMethod: 'ALPHABETICAL'
            }
        }
    }

    post {
        always {
            echo "Cleaning up workspace..."
            cleanWs()
        }
        success {
            echo "Pipeline completed successfully!"
        }
        failure {
            echo "Pipeline failed. Check logs."
        }
    }
}
