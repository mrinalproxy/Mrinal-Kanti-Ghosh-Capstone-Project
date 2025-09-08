pipeline {
    agent any

    tools {
        jdk 'jdk17'
        maven 'Maven3'
    }

    stages {
        stage('Checkout') {
            steps {
                echo "Cloning GitHub repository..."
                git url: 'https://github.com/mrinalproxy/Mrinal-Kanti-Ghosh-Capstone-Project.git', branch: 'main'
            }
        }

        stage('Clean and Build') {
            steps {
                echo "Cleaning and building project using Maven..."
                bat "mvn clean compile"
            }
        }

        stage('Run Tests') {
            steps {
                echo "Running TestNG tests..."
                bat "mvn test"
            }
        }

        stage('Generate Report') {
            steps {
                echo "Publishing TestNG HTML report and archiving test-output..."
                // Publish HTML report (HTML Publisher plugin required)
                publishHTML([allowMissing: true,
                             alwaysLinkToLastBuild: true,
                             keepAll: true,
                             reportDir: 'test-output',
                             reportFiles: 'index.html',
                             reportName: 'TestNG Report'])
                // Archive all files under test-output for download
                archiveArtifacts artifacts: 'test-output/**', fingerprint: true
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
            echo " Pipeline failed. Check logs."
        }
    }
}
