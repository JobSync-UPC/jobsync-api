package com.jobsync.jobysncapi.task.api.dto.request;

import lombok.Data;

@Data
public class TaskRequest {

    private String title;
    private String description;
    private Long interviewId;
    private Long evaluationId;
}
