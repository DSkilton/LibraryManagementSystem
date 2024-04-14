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
                        chmod +x gradlew
                        ls -l gradlew
                        echo "Verifying Gradle wrapper execution..."
                        ./gradlew --version
                        '''
                    }
                }
            }
        }

        stage('Build Environment') {
            steps {
                script {
                    dir('librarymanagementsystem') {
                        echo "Running Gradle clean build..."
                        sh '''
                        ./gradlew clean build
                        echo "Displaying directory structure post-build..."
                        find . -type f -print | grep -v "\\.git"
                        echo "Listing build artifacts..."
                        ls -l build/libs/
                        '''
                        echo "Building Docker image ${env.DOCKER_IMAGE}..."
                        docker.build(env.DOCKER_IMAGE, '.')
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
                        sh 'ls -al | grep -v "/$" '
                        echo "List files and their permissions in build/libs/"
                        sh 'ls -al | grep -v "/$" '                    }
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

        /* stage('Deploy to Kubernetes') {
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
        } */
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
