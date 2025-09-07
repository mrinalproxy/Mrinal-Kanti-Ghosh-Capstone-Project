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
                git url: 'https://github.com/mrinalproxy/Mrinal-Kanti-Ghosh-Capstone-Project.git', branch: 'main'
            }
        }

        stage('Clean and Build') {
            steps {
                echo "Cleaning and building project using Maven..."
                bat "mvn clean compile" // Windows
            }
        }

        stage('Run Tests') {
            steps {
                echo "Running TestNG + Cucumber tests..."
                bat "mvn test" // Windows
            }
        }

        stage('Generate Report') {
            steps {
                echo "Publishing Cucumber HTML report..."
                cucumber fileIncludePattern: '**/cucumber.json',
                         jsonReportDirectory: 'target/cucumber-reports',
                         reportTitle: 'Cucumber Report',
                         buildStatus: 'UNSTABLE'
            }
        }
    }

    post {
        always {
            echo "Cleaning up workspace..."
            cleanWs()
        }
        success {
            echo "✅ Pipeline completed successfully!"
        }
        failure {
            echo "❌ Pipeline failed. Check logs."
        }
    }
}
