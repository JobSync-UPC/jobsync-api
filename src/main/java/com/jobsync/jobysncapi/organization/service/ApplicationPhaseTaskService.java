package com.jobsync.jobysncapi.organization.service;

import com.jobsync.jobysncapi.organization.api.dto.request.ApplicationPhaseTaskRequest;
import com.jobsync.jobysncapi.organization.api.dto.response.ApplicationPhaseTaskResponse;
import com.jobsync.jobysncapi.organization.domain.model.entity.ApplicationPhaseTask;

import java.util.Optional;

public interface ApplicationPhaseTaskService {

    public abstract Iterable<ApplicationPhaseTaskResponse> getApplicationPhaseTasks();
    public abstract Optional<ApplicationPhaseTaskResponse> getApplicationPhaseTaskById(Long applicationPhaseTaskId);

    public abstract ApplicationPhaseTask createApplicationPhaseTask(ApplicationPhaseTaskRequest applicationPhaseTaskRequest);

    public abstract ApplicationPhaseTask updateApplicationPhaseTask(Long applicationPhaseTaskId, ApplicationPhaseTaskRequest applicationPhaseTaskRequest);

    public abstract void deleteApplicationPhaseTask(Long applicationPhaseTaskId);
}
