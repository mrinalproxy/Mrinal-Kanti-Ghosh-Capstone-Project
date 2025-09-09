pipeline {
    agent any

    tools {
        jdk 'jdk17'
        maven 'Maven3'
    }

    environment {
        BRANCH_NAME = 'main'
        COMMIT_MESSAGE = 'Jenkins: Auto-commit TestNG reports after build'
    }

    // Poll SCM every 15 mins for changes
    triggers {
        pollSCM('H/15 * * * *')
    }

    stages {
        stage('Checkout') {
            steps {
                echo "Cloning GitHub repository..."
                git branch: "${env.BRANCH_NAME}",
                    url: 'https://github.com/mrinalproxy/Mrinal-Kanti-Ghosh-Capstone-Project.git'
            }
        }

        stage('Clean and Build') {
            steps {
                echo "Cleaning and compiling project..."
                bat "mvn clean compile"
            }
        }

        stage('Run Tests') {
            steps {
                echo "Running TestNG tests..."
                bat "mvn test -DsuiteXmlFile=src/test/resources/testng.xml"
            }
        }

        stage('Publish Reports') {
            steps {
                echo "Publishing TestNG reports..."
                publishHTML([allowMissing: true,
                             alwaysLinkToLastBuild: true,
                             keepAll: true,
                             reportDir: 'test-output',
                             reportFiles: 'index.html',
                             reportName: 'TestNG Report'])
                archiveArtifacts artifacts: 'test-output/**', fingerprint: true
            }
        }

        stage('Commit & Push Reports') {
            steps {
                script {
                    echo "Adding, committing and pushing reports back to GitHub..."

                    withCredentials([usernamePassword(
                        credentialsId: 'github-jenkins',
                        usernameVariable: 'GIT_USER',
                        passwordVariable: 'GIT_TOKEN')]) {

                        bat """
                            git config user.email "mrinalghosh360@gmail.com"
                            git config user.name "Mrinal Kanti Ghosh"

                            git status
                            git add test-output/*

                            REM Commit only if there are changes
                            git diff --cached --quiet || git commit -m "${COMMIT_MESSAGE}"

                            REM Push using token
                            git push https://%GIT_USER%:%GIT_TOKEN%@github.com/mrinalproxy/Mrinal-Kanti-Ghosh-Capstone-Project.git ${BRANCH_NAME}
                        """
                    }
                }
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
