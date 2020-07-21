pipeline {
   agent any

   stages {
      stage('Git Checkout') {
         steps {
            // Get some code from a GitHub repository
            git 'https://github.com/aalhabib001/To-Let-App-Backend.git'

         }
      }
      //stage('test') {
       //  steps {

            // Run Maven on a Unix agent.
       //     sh "mvn test"

       //  }
      //}
      stage('Build') {
         steps {

            // Run Maven on a Unix agent.
            sh "mvn clean package"

         }
      }

      //stage("Staging") {
         //       sh "pid=\$(lsof -i:8989 -t); kill -TERM \$pid "
         //         + "|| kill -KILL \$pid"
         //       withEnv(['JENKINS_NODE_COOKIE=dontkill']) {
          //          sh 'nohup ./mvnw spring-boot:run -Dserver.port=8989 &'
         //       }
          //  }
      stage('Deploy') {
         steps {
             //
             //Run Maven on a Unix agent.

             sh """
            JENKINS_NODE_COOKIE=dontKillMe nohup java -jar ./target/ToLetProject-0.0.1-SNAPSHOT.jar &
            fuser -k 8585/tcp
            JENKINS_NODE_COOKIE=dontKillMe nohup java -jar ./target/ToLetProject-0.0.1-SNAPSHOT.jar &
            """

         }
      }
   }
}
