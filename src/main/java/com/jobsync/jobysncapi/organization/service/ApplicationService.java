package com.jobsync.jobysncapi.organization.service;

import com.jobsync.jobysncapi.organization.api.dto.request.ApplicationRequest;
import com.jobsync.jobysncapi.organization.api.dto.response.ApplicationResponse;
import com.jobsync.jobysncapi.organization.domain.model.entity.Application;

import java.util.List;

public interface ApplicationService {
    public abstract Iterable<ApplicationResponse> getAllApplications();
    public abstract ApplicationResponse getApplicationById(Long applicationId);
    public abstract Application applyForJob(ApplicationRequest applicationRequest);
    public abstract Iterable<ApplicationResponse> getApplicationsByApplicantId(Long id);
    public abstract Iterable<ApplicationResponse> getApplicationsByRecruitmentProcessId(Long id);
    public abstract Application updateApplicationPhase(Long applicationId, Long newPhaseId);
    public abstract Application deactivateApplication(Long applicationId);
}
