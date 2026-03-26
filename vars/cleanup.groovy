def cleanupImages() {
    sh """
        podman system prune -a -f|| true
        podman container prune -f || true
    """
}
