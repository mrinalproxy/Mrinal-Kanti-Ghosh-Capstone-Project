pipeline {
    agent any

    tools {
        maven 'Maven3'   // Name must match your Jenkins Maven installation
        jdk 'jdk11'      // Name must match your Jenkins JDK installation
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/mrinalproxy/Mrinal-Kanti-Ghosh-Wipro-Capstone-Project.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Run Tests') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Publish Reports') {
            steps {
                publishHTML(target: [
                    allowMissing: true,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'target/cucumber-reports',
                    reportFiles: 'index.html',
                    reportName: 'Cucumber Report'
                ])
            }
        }
    }
}
