pipeline {
    agent {
      node {
        label "win_slave"
      }
    }

    options { skipDefaultCheckout() }

    environment {
    DOCKER_HUB_REPO = "https://hub.docker.com/repository/docker"
    DOCKER_HUB_NAMESPACE = "abelasuvalenteen"
    IMAGE_NAME = "fibonacci"
    VERSION = "1.0"
    }

    stages {
        stage('Build') {
            steps {
                // Clean Workspace before start
                cleanWs()
                // Get code from GitHub repository
                git(
                    url: 'https://github.com/abelasuvalenteen/fibonacci.git',
                    branch: 'main'
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
                script{
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
                             // docker build and tag image
                             bat "docker build -t ${DOCKER_HUB_NAMESPACE}/${IMAGE_NAME}:${VERSION} ."
                             // docker push tagged image
                             bat "docker push ${DOCKER_HUB_NAMESPACE}/${IMAGE_NAME}:${VERSION}"
                             // docker list images
                             bat "docker images"
                             // docker run
                             bat "docker run -ti -p8085:8085 ${IMAGE_NAME}:${VERSION}"
                         }
                     }
                }
            }
        }
    }
}
