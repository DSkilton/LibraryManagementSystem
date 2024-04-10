pipeline {
    agent any

    tools {
        jdk 'JDK8'
        gradle 'Gradle 6.8'
    }

    environment {
        DOCKER_IMAGE = 'skilton/library-management-system:latest'
        REGISTRY_CREDENTIALS_ID = 'docker-hub-credentials'
        DOCKER_REGISTRY = 'https://registry.hub.docker.com'
    }

    stages {
        stage('Preparation') {
            steps {
                // Clean the workspace before doing anything
                cleanWs()
            }
        }

        stage('Clone Repository') {
            steps {
                // Checks out the project from the specified Git repository
                git url: 'https://github.com/DSkilton/LibraryManagementSystem.git', branch: 'master'
            }
        }

        stage('Build and unit test') {
            steps {
                // Build the project and run unit tests
                sh './gradlew clean build'
            }
        }

        stage('Build Docker Image') {
            steps {
                // Building the Docker image
                script {
                    docker.build(env.DOCKER_IMAGE)
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                // Logging into Docker Hub and pushing the image
                script {
                    docker.withRegistry(env.DOCKER_REGISTRY, env.REGISTRY_CREDENTIALS_ID) {
                        docker.image(env.DOCKER_IMAGE).push()
                    }
                }
            }
        }

        /*
        stage('Deploy to Kubernetes') {
            steps {
                script {
                    withKubeConfig(credentialsId: 'kubeconfig') {
                        sh 'kubectl apply -f k8s/' // Runs kubectl apply command where k8s is the directory containing all your yaml files for Kubernetes deployment
                    }
                }
            }
        }
        */
    }

    post {
        // Defines post-build actions
        success {
            echo 'Build and Deployment Successful!'
        }
        failure {
            echo 'Build or Deployment Failed.'
        }
        always {
            echo 'Cleaning up...'
            cleanWs() // Cleanup workspace after build
        }
    }
}
