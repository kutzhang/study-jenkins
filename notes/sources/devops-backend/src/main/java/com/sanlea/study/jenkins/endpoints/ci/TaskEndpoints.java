package com.sanlea.study.jenkins.endpoints.ci;

import com.sanlea.study.jenkins.endpoints.ci.dto.CreateTaskRequest;
import com.sanlea.study.jenkins.support.api.EndpointResponse;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.UUID;

import static java.text.MessageFormat.format;

@Path("/ci/task")
public class TaskEndpoints {

    @Inject
    Logger logger;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public EndpointResponse<String> create(@Valid CreateTaskRequest request) {
        var taskId = UUID.randomUUID().toString();

        logger.info(format("Task for project={0} created: {1}", request.getProjectName(), taskId));

        return EndpointResponse.<String>builder()
                .data(taskId)
                .build();
    }
}
