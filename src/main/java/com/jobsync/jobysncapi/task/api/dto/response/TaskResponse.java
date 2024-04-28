package com.jobsync.jobysncapi.task.api.dto.response;

import com.jobsync.jobysncapi.task.domain.model.entity.Evaluations;
import com.jobsync.jobysncapi.task.domain.model.entity.Interviews;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

public class TaskResponse {

    private Long id;
    private String title;
    private String description;
    @OneToOne
    @JoinColumn(name = "evaluations_id", referencedColumnName = "id")
    private Evaluations evaluations;

    @OneToOne
    @JoinColumn(name = "interviews_id", referencedColumnName = "id")
    private Interviews interviews;
}
