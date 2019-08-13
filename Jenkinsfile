pipeline {
  agent any
  stages {
    stage('clean') {
      steps {
        sh './gradlew clean --stacktrace'
      }
    }
    stage('Unit tests Data') {
      steps {
        sh './gradlew data:clean data:testDebugUnitTest --stacktrace'
      }
    }
    stage('Unit Test Domain') {
      steps {
        sh './gradlew domain:clean domain:testDebugUnitTest --stacktrace'
      }
    }
  }
}
