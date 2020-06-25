pipeline {
   agent any

   stages {
      stage('Git Checkout') {
         steps {
            // Get some code from a GitHub repository
            git 'https://github.com/aalhabib001/To-Let-App-Backend.git'

         }
      }
      stage('test') {
         steps {

            // Run Maven on a Unix agent.
            sh "mvn test"

         }
      }
      stage('Build') {
         steps {

            // Run Maven on a Unix agent.
            sh "mvn clean package"

         }
      }
      stage('Deploy') {
         steps {

            // Run Maven on a Unix agent.
            sh "mvn clean package"

         }
      }
   }
}