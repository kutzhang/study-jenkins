package com.sanlea.study.jenkins

class MessageManager {
    static void say(script, message) {
        script.sh "message manager says: ${message}"
    }
}