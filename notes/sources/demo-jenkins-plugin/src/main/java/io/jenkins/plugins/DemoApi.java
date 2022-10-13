package io.jenkins.plugins;

import com.cloudbees.hudson.plugins.folder.Folder;
import hudson.model.*;
import jenkins.model.Jenkins;
import org.kohsuke.stapler.StaplerRequest;
import org.kohsuke.stapler.StaplerResponse;
import org.kohsuke.stapler.WebMethod;

import java.io.IOException;
import java.util.List;

public class DemoApi extends Api {
    public DemoApi(Object bean) {
        super(bean);
    }

    @WebMethod(name = "hello-world")
    public void helloWorld(StaplerRequest request, StaplerResponse response) throws IOException {
        Jenkins jenkins = Jenkins.get();
        jenkins.createProject(Folder.class, "fuck-you");
        List<Item> items = jenkins.getAllItems();
        StringBuilder builder = new StringBuilder();
        items.forEach(item -> builder.append(item.getName()).append(","));
        builder.append("\n");
        response.getWriter().write(builder.toString());
    }

    @WebMethod(name = "create-folder")
    public void createFolder(StaplerRequest request, StaplerResponse response) throws IOException {
        String name = request.getParameter("name");
        Jenkins jenkins = Jenkins.get();
        jenkins.createProject(Folder.class, name);
        response.getWriter().write("OK");
    }
}
