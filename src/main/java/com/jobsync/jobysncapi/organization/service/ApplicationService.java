package com.jobsync.jobysncapi.organization.service;

import com.jobsync.jobysncapi.organization.api.dto.request.ApplicationRequest;
import com.jobsync.jobysncapi.organization.api.dto.response.ApplicationResponse;
import com.jobsync.jobysncapi.organization.domain.model.entity.Application;

public interface ApplicationService {


    public abstract Application createApplication(ApplicationRequest applicationRequest, Long id);

    public abstract Application updateApplication(Long id, ApplicationRequest applicationRequest);

    public abstract void deleteApplication(Long applicationId);

    public abstract Iterable<ApplicationResponse> getAllApplications();

    public abstract ApplicationResponse getApplicationById(Long applicationId);

}
