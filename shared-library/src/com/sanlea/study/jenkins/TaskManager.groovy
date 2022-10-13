package com.sanlea.study.jenkins

import groovy.json.JsonOutput
import groovy.json.JsonSlurper

class TaskManager {
    /**
     * create
     * 
     * @return task id
     */
    static String create(script, String projectName) {
        def post = (HttpURLConnection) new URL("http://192.168.10.174:8090/api/ci/task").openConnection()
        def parameters = [
                "projectName": "demox"
        ]
        def content = JsonOutput.toJson(parameters);

        post.setRequestProperty("Content-Type", "application/json")
        post.setRequestMethod("POST")
        post.setDoOutput(true)
        post.getOutputStream().write(content.getBytes("UTF-8"))

        def json = new JsonSlurper();
        def response = json.parseText(post.getInputStream().text)
        def taskId = response.data

        script.echo "task id: ${response.data}"
        
        return taskId
    }
}