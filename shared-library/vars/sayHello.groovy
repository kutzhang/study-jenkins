def call(script, message) {
    script.sh 'env'
    script.echo "sayHello: ${message}"
}