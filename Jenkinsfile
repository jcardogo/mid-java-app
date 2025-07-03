pipeline {
    agent any

    tools {
        gradle 'Gradle_8' // Name of Gradle version configured in Jenkins
    }

    stages {
        stage('Clone') {
            steps {
                git url: 'https://github.com/jcardogo/mid-java-app.git', branch: 'master'
            }
        }

        stage('Build') {
            steps {
                sh './gradlew clean build'
            }
        }

        stage('Fat JAR') {
            steps {
                sh './gradlew shadowJar'
            }
        }

        stage('Archive') {
            steps {
                archiveArtifacts artifacts: 'build/libs/*.jar', fingerprint: true
            }
        }
    }
}
