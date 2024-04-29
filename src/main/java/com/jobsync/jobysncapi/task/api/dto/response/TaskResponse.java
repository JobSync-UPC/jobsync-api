package com.jobsync.jobysncapi.task.api.dto.response;

import com.jobsync.jobysncapi.task.domain.model.entity.Evaluation;
import com.jobsync.jobysncapi.task.domain.model.entity.Interview;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

public class TaskResponse {

    private Long id;
    private String title;
    private String description;
    @OneToOne
    @JoinColumn(name = "evaluations_id", referencedColumnName = "id")
    private Evaluation evaluation;

    @OneToOne
    @JoinColumn(name = "interviews_id", referencedColumnName = "id")
    private Interview interview;
}
