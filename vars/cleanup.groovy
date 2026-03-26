def cleanupImages() {
    sh """
        podman image prune -a -f || true
        podman container prune -f || true
    """
}
