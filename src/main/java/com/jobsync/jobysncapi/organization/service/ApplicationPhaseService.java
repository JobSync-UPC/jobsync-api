package com.jobsync.jobysncapi.organization.service;

import com.jobsync.jobysncapi.organization.api.dto.request.ApplicationPhaseRequest;
import com.jobsync.jobysncapi.organization.api.dto.response.ApplicationPhaseResponse;
import com.jobsync.jobysncapi.organization.domain.model.entity.ApplicationPhase;

import java.util.List;
import java.util.Optional;

public interface ApplicationPhaseService {

    public abstract Iterable<ApplicationPhaseResponse> getApplicationPhases();
    public abstract Optional<ApplicationPhaseResponse> getApplicationPhaseById(Long applicationPhaseId);

    public abstract ApplicationPhase createApplicationPhase(ApplicationPhaseRequest applicationPhaseRequest);

    public abstract ApplicationPhase updateApplicationPhase(Long applicationPhaseId, ApplicationPhaseRequest applicationPhaseRequest);

    public abstract void deleteApplicationPhase(Long applicationPhaseId);
}
