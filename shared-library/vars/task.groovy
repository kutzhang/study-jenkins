import om.sanlea.study.jenkins.component.ApiClient

void createTask(String projectName, Closure closure) {
    def apiClient = new ApiClient()
    def response = apiClient.request("http://192.168.10.174:8090/api/ci/task", "POST", [
        "projectName": projectName
    ])
    closure(response.data)
}