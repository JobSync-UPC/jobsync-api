package com.jobsync.jobysncapi.task.api.dto.request;

import lombok.Data;

@Data
public class UpdateTaskRequest {

    private Long taskId;
    private String title;
    private String description;
    private Long interviewId;
    private Long evaluationId;
}
