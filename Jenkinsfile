pipeline {
    agent any

    environment {
        DOCKERHUB_CREDENTIALS_ID = 'dockerhub-jcardogo' // ID of the Docker Hub credentials stored in Jenkins
        IMAGE_NAME = 'mid-java-gradle-app' // Name of the Docker image to build
        IMAGE_TAG = "${BUILD_NUMBER}" // Tag for the Docker image, using the Jenkins build number
    }

    tools {
        gradle 'Gradle_8' // Name of Gradle version configured in Jenkins
    }

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/jcardogo/mid-java-app.git', branch: 'master'
            }
        }

        stage('Build JAR') {
            steps {
                sh './gradlew shadowJar'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t $IMAGE_NAME:$IMAGE_TAG .'
            }
        }

        stage('Login to Docker Hub') {
            steps {
                withCredentials([usernamePassword(credentialsId: DOCKERHUB_CREDENTIALS_ID, usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                    sh 'echo $DOCKER_PASSWORD | docker login -u $DOCKER_USERNAME --password-stdin'
                }
            }
        }
        stage('Push to Docker Hub') {
            steps {
                sh 'docker push $IMAGE_NAME:$IMAGE_TAG'
                sh 'docker tag $IMAGE_NAME:$IMAGE_TAG $DOCKERHUB_CREDENTIALS_ID/$IMAGE_NAME:$IMAGE_TAG'
                sh 'docker push $DOCKERHUB_CREDENTIALS_ID/$IMAGE_NAME:$IMAGE_TAG'
            }
        }

        stage('Deploy container') {
            steps {
                ssh '''
                    docker pull $DOCKERHUB_CREDENTIALS_ID/$IMAGE_NAME:$IMAGE_TAG
                    docker stop mid-java-gradle-app || true
                    docker rm mid-java-gradle-app || true
                    docker run -d --name mid-java-gradle-app -p 8085:8080 $DOCKERHUB_CREDENTIALS_ID/$IMAGE_NAME:$IMAGE_TAG
                '''
            }
        }

        stage('Archive') {
            steps {
                archiveArtifacts artifacts: 'build/libs/*.jar', fingerprint: true
            }
        }

        post {
            always {
                sh 'docker logout'
            }
        }
    }
}
