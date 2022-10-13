package io.jenkins.plugins;

import edu.umd.cs.findbugs.annotations.NonNull;
import hudson.EnvVars;
import hudson.Extension;
import hudson.FilePath;
import hudson.Launcher;
import hudson.model.AbstractProject;
import hudson.model.Result;
import hudson.model.Run;
import hudson.model.TaskListener;
import hudson.search.Search;
import hudson.tasks.BuildStepDescriptor;
import hudson.tasks.Publisher;
import hudson.tasks.Recorder;
import jenkins.model.Jenkins;
import jenkins.tasks.SimpleBuildStep;
import org.jenkinsci.Symbol;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.DataBoundSetter;

import java.io.PrintStream;

import static hudson.model.Result.FAILURE;
import static java.text.MessageFormat.format;

public class SayHelloBuildStep extends Recorder implements SimpleBuildStep {

    private String message;

    @DataBoundConstructor
    public SayHelloBuildStep() {
        this.message = "Fuck you!!";
    }

    public String getMessage() {
        return message;
    }

    @DataBoundSetter
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public void perform(@NonNull Run<?, ?> run,
                        @NonNull FilePath workspace,
                        @NonNull EnvVars env,
                        @NonNull Launcher launcher,
                        @NonNull TaskListener listener) {
        PrintStream logger = listener.getLogger();
        String content = format("[message] {0}-{1}: {2}", env.get("JOB_NAME"), run.getId(), message);
        logger.println(content);

        // 没用的，无法生效，更变不了环境变量
        env.put("FUCK_TARGET_NAME", "ABC");
        run.setResult(FAILURE);
    }

    @Extension
    @Symbol("sayHello")
    public static class DescriptorImpl extends BuildStepDescriptor<Publisher> {

        public DescriptorImpl() {
            super(SayHelloBuildStep.class);
        }

        @Override
        public boolean isApplicable(Class<? extends AbstractProject> jobType) {
            return true;
        }

        @NonNull
        @Override
        public String getDisplayName() {
            return "Say Hello";
        }
    }
}
