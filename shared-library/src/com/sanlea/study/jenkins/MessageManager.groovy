package com.sanlea.study.jenkins

class MessageManager {
    static def say(script, content) {
        script.echo "message manager says: ${content}"
    }
}