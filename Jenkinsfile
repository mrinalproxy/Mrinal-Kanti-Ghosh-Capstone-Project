pipeline {
    agent any

    tools {
        maven 'Maven3'   // name must match the Maven tool you set up in Jenkins
        jdk 'jdk11'      // name must match the JDK you set up in Jenkins
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/mrinalproxy/Mrinal-Kanti-Ghosh-Wipro-Capstone-Project.git'
            }
        }

        stage('Build & Test') {
            steps {
                sh 'mvn clean test'
            }
        }

        stage('Cucumber HTML Report') {
            steps {
                publishHTML([
                    allowMissing: false,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'target/cucumber-reports',
                    reportFiles: 'cucumber.html',
                    reportName: 'Cucumber Report'
                ])
            }
        }
    }

    post {
        always {
            junit 'target/surefire-reports/*.xml'
        }
        success {
            echo '✅ Build & Tests Passed!'
        }
        failure {
            echo '❌ Build Failed!'
        }
    }
}
