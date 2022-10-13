package com.sanlea.study.jenkins.endpoints.ci.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CreateTaskRequest {

    // project name
    @NotBlank(message = "Project name must not be blank")
    private String projectName;
}
