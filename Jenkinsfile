//Local Variables
DOCKER_HUB_REPO = "https://hub.docker.com/repository/docker"
DOCKER_HUB_NAMESPACE = "abelasuvalenteen"
IMAGE_NAME = "fibonacci"
VERSION = "1.0"

def callMavenSonarScan() {
    // Clean Workspace before start
    cleanWs()

   // Get code from GitHub repository
   git(
    url: 'https://github.com/abelasuvalenteen/fibonacci.git',
    branch: 'main'
    )

    withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'sonar-creds', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']]) {
        // To run Maven on a Windows agent, use
        bat "mvn -Dmaven.test.failure.ignore=true clean install sonar:sonar -Dsonar.host.url=http://localhost:9000 -Dsonar.login=$PASSWORD"
    }
}


def callLocalBuild () {
    // Clean Workspace before start
    cleanWs()
   // Get code from GitHub repository
   git(
    url: 'https://github.com/abelasuvalenteen/fibonacci.git',
    branch: 'main'
    )

    // To run Maven on a Windows agent, use
    bat "mvn -Dmaven.test.failure.ignore=true clean package"

    archiveArtifacts 'target/*.jar'
}

def callDockerBuild () {
      // Clean Workspace before start
      cleanWs()
     // Check docker version
     bat "docker --version"
     dir("${WORKSPACE}") {
         git(
             url: 'https://github.com/abelasuvalenteen/fibonacci.git',
             branch: 'main'
         )
         withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'dockerhub-creds', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']]) {
             // docker hub login
             bat "docker login -u $USERNAME -p $PASSWORD"

             try {
                // Kill the container if any running
                bat "docker container kill fibonacci"
                // Remove the container
                bat "docker container rm fibonacci"
             } catch (Exception e) {
                 // If no container running continue
                 echo "Do Nothing"
             }

             // docker build and tag image
             bat "docker build --no-cache -t ${DOCKER_HUB_NAMESPACE}/${IMAGE_NAME}:${VERSION} ."
             // docker push tagged image
             bat "docker push ${DOCKER_HUB_NAMESPACE}/${IMAGE_NAME}:${VERSION}"
             // docker list images
             bat "docker images"
             // docker run
             bat "docker run --name fibonacci -t -d -p 8181:8181/tcp ${DOCKER_HUB_NAMESPACE}/${IMAGE_NAME}:${VERSION}"
         }
     }
}

pipeline {
    agent {
      node {
        label "win_slave"
      }
    }

    parameters {
        string(defaultValue: "docker", description: "Input how to build: local or docker", name: "buildType")
    }

    options { skipDefaultCheckout() }

    stages {
        stage('Code Quality Scan') {
            steps {
               script {
                   echo "Call docker build"
                   callMavenSonarScan()
               }
            }
        }
        stage('Build') {
            steps {
               script {
                  if("${params.buildType}".equalsIgnoreCase("local")) {
                   echo "Call local Maven Build"
                   callLocalBuild()
                  } else {
                   echo "Call docker build"
                   callDockerBuild()
                  }
               }
            }
        }
    }

    post {
        success {
            echo "Job Success"
        }
        failure {
            echo "Job Failed"
        }
    }
}
