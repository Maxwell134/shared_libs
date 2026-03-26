def call(Map pipelineParams = [:]) {
    def customWs = "/scm/checkout/TIM/${env.JOB_NAME}"
    
    pipeline {

        agent {
        node {
            label 'linux_node'
            customWorkspace customWs
             }
          }

        environment {            
            
            DOCKER_IMAGE = "${pipelineParams.DOCKER_IMAGE ?: 'nginx'}"
            DOCKER_TAG   = "${pipelineParams.DOCKER_TAG ?: 'latest'}"
        }

        stages {
            stage('Print Values') {
                steps {
                    echo "Workspace: ${env.WORKSPACE}"
                    
                        echo "Image: ${env.DOCKER_IMAGE}"
                    echo "Tag: ${env.DOCKER_TAG}"
                }
            }
            stage('login to docker') {
                steps {
                    script {
                    def status = dockerLogin.loginDocker()
                    if (status) {
                        sh """
                        podman build -t ${env.DOCKER_IMAGE}:${env.DOCKER_TAG} .
                        """
                    } else {
                        error "Docker login failed"


                    }
                
                }
            }
            

        }
            
        }

    }
}
