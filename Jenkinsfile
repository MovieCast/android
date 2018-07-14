pipeline {
  agent {
    node {
      label 'android'
    }

  }
  stages {
    stage('Build') {
      steps {
        sh 'chmod +x gradlew'
        sh './gradlew clean'
        sh './gradlew assembleDebug'
      }
    }
    stage('Archive') {
      steps {
        archiveArtifacts '**/*.apk'
      }
    }
  }
}