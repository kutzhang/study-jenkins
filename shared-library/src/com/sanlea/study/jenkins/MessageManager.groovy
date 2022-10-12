package com.sanlea.study.jenkins

class MessageManager {
    static def say(script, message) {
        script.sh "message manager says: ${message}"
    }
}