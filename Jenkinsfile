pipeline {
    agent {
      node {
        label "win_slave"
      }
    }

    stages {
        stage('Build') {
            steps {
                // Get code from GitHub repository
                git(
                    url: 'https://github.com/abelasuvalenteen/fibonacci.git',
                    branch: '*/main'
                    )

                // To run Maven on a Windows agent, use
                bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }

            post {
                success {
                    archiveArtifacts 'target/*.jar'
                }
            }
        }

        stage('Dockerize') {
            steps {
                 bat "docker -version"
            }
        }
    }
}
