pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'skilton/library-management-system:latest'
    }

    stages {
       stage('Setup Debug') {
           steps {
               script {
                   echo "Current directory: ${pwd()}"
               }
           }
       }

        stage('Clone repository') {
            steps {
                echo 'Cloning the repository...'
                dir('LibMan') {
                    git branch: 'master',
                     url: 'https://github.com/DSkilton/LibraryManagementSystem'
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    try {
                        echo "Building Docker image ${env.DOCKER_IMAGE}..."
                        docker.build(env.DOCKER_IMAGE)
                        echo "Image built successfully."
                    } catch (Exception e) {
                        echo "Error building Docker image: ${e.getMessage()}"
                        throw e // rethrow the error to fail the build
                    }
                }
            }
        }

        stage('Test') {
            steps {
                echo 'Running tests...'
                sh './gradlew test'
            }
        }

        stage('Publish Docker Image') {
            steps {
                script {
                    echo "Publishing Docker image..."
                    docker.withRegistry('https://registry.hub.docker.com', 'docker-hub-credentials') {
                        docker.image(env.DOCKER_IMAGE).push()
                    }
                }
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                echo 'Deploying to Kubernetes...'
                script {
                    kubernetesDeploy(
                        configs: 'kubeconfig',
                        kubeconfigId: 'kubeconfig-id'
                    )
                }
            }
        }
    }

    post {
        always {
            echo 'Cleaning up workspace...'
            cleanWs()
        }

        success {
            echo 'Build completed successfully.'
        }

        failure {
            echo 'Build failed. Check the logs for details.'
        }
    }
}
