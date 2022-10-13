package io.jenkins.plugins;

import hudson.Plugin;
import hudson.model.Api;

public class DemoPlugin extends Plugin {

    public Api getApi() {
        return new DemoApi(this);
    }
}
