def loginDocker() {

    def checkStatus = false

    withCredentials([usernamePassword(
        credentialsId: 'loginPassword',
        usernameVariable: 'USERNAME',
        passwordVariable: 'PASSWORD'
    )]) {

        def result = sh(
            script: "podman login -u $USERNAME -p $PASSWORD",
            returnStatus: true
        )

        if (result == 0) {
            checkStatus = true
        } else {
            checkStatus = false
        }
    }

    return checkStatus
}
