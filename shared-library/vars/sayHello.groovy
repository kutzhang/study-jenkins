def call(script, message) {
    script.sh 'env'
    script.echo "message: ${message}"
}