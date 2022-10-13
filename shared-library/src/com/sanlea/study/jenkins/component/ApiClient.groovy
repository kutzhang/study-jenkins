package com.sanlea.study.jenkins.component

import groovy.json.JsonOutput
import groovy.json.JsonSlurper

class ApiClient {

    Object request(String url, String method, Map parameters) {
        def request = (HttpURLConnection) new URL(url).openConnection()
        request.setRequestProperty("Content-Type", "application/json")
        request.setRequestMethod(method)
        request.setDoOutput(true)
        request.getOutputStream().write(JsonOutput.toJson(parameters).getBytes("UTF-8"))

        def json = new JsonSlurper();
        return json.parseText(post.getInputStream().text)
    }
}