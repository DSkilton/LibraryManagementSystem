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
                dir('librarymanagementsystem') {
                    git branch: 'master',
                     url: 'https://github.com/DSkilton/LibraryManagementSystem'
                }
            }
        }

        stage('Check Environment') {
            steps {
                script {
                    dir('librarymanagementsystem') {
                        sh 'pwd'
                        sh 'ls -l build/libs/'
                    }
                }
            }
        }

        stage('Verify Gradlew Configuration') {
            steps {
                script {
                    sh '''
                    ls -l gradlew
                    ./gradlew --version
                    '''
                }
            }
        }

        stage('Build and Check Environment') {
            steps {
                script {
                    dir('librarymanagementsystem') {
                        echo "Running Gradle clean build..."
                        sh './gradlew clean build'
                        echo "Current directory post-build: ${pwd()}"
                        sh 'ls -l build/libs/'
                        echo "Building Docker image ${env.DOCKER_IMAGE}..."
                        docker.build(env.DOCKER_IMAGE, "build/libs")
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
