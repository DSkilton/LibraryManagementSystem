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
                    sh 'ls -al' //lists files and their permissions
                }
            }
        }

        stage('Clone repository') {
            steps {
                echo 'Cloning the repository...'
                dir('librarymanagementsystem') {
                    git branch: 'master',
                        url: 'https://github.com/DSkilton/LibraryManagementSystem'
                    echo "Repository cloned."
                    sh 'ls -l'
                }
            }
        }

        stage('Verify Gradlew Configuration') {
            steps {
                script {
                    dir('librarymanagementsystem') {
                        sh '''
                        echo "Checking gradlew permissions..."
                        ls -l gradlew
                        echo "Verifying Gradle wrapper execution..."
                        ./gradlew --version
                        '''
                    }
                }
            }
        }

        stage('Check Environment') {
            steps {
                script {
                    dir('librarymanagementsystem') {
                        echo "Checking build environment..."
                        sh 'pwd'
                        echo "List files and their permissions"
                        sh 'ls -al'

                        echo "List files and their permissions in build/libs/"
                        sh 'ls -l build/libs/'
                    }
                }
            }
        }

        stage('Build and Check Environment') {
            steps {
                script {
                    dir('librarymanagementsystem') {
                        echo "Running Gradle clean build..."
                        sh '''
                        ./gradlew clean build
                        echo "Displaying directory structure post-build..."
                        find . -type f -print
                        echo "Listing build artifacts..."
                        ls -l build/libs/
                        '''
                        echo "Building Docker image ${env.DOCKER_IMAGE}..."
                        docker.build(env.DOCKER_IMAGE, '.')
                    }
                }
            }
        }

        stage('Test') {
            steps {
                script {
                    dir('librarymanagementsystem') {
                        echo "Running tests..."
                        sh './gradlew test'
                        echo "Tests completed."
                    }
                }
            }
        }

        stage('Publish Docker Image') {
            steps {
                script {
                    echo "Publishing Docker image..."
                    docker.withRegistry('https://registry.hub.docker.com', 'docker-hub-credentials') {
                        docker.image(env.DOCKER_IMAGE).push()
                        echo "Docker image published."
                    }
                }
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                script {
                    echo 'Deploying to Kubernetes...'
                    kubernetesDeploy(
                        configs: 'kubeconfig',
                        kubeconfigId: 'kubeconfig-id'
                    )
                    echo "Deployment to Kubernetes initiated."
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
