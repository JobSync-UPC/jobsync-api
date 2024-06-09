package com.jobsync.jobysncapi.organization.api.rest;

import com.jobsync.jobysncapi.organization.api.dto.request.ApplicationRequest;
import com.jobsync.jobysncapi.organization.api.dto.response.ApplicationResponse;
import com.jobsync.jobysncapi.organization.domain.model.entity.Application;
import com.jobsync.jobysncapi.organization.service.ApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Applications Controller", description = "Create, read, update, and delete applications")
@RestController
@RequestMapping("/api/v1/applications")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    private final ModelMapper modelMapper;

    public ApplicationController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Operation(summary = "Get all applications")
    @Transactional(readOnly = true)
    @GetMapping("/")
    public Iterable<ApplicationResponse> getAllApplications() {
        return applicationService.getAllApplications();
    }


    @Operation(summary = "Apply for a job by recruitment process id")
    @Transactional
    @PostMapping("/apply")
    public ResponseEntity<ApplicationResponse> applyForJob(@RequestBody ApplicationRequest applicationRequest) {
        ValidateApplicationRequest(applicationRequest);
        Application application = applicationService.applyForJob(applicationRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(application, ApplicationResponse.class));
    }

    @Operation(summary = "Get applications by applicant id")
    @Transactional(readOnly = true)
    @GetMapping("/applicant/{id}")
    public Iterable<ApplicationResponse> getApplicationsByApplicantId(@PathVariable Long id) {
        return applicationService.getApplicationsByApplicantId(id);
    }

    @Operation(summary = "Get applications by recruitment process id")
    @Transactional(readOnly = true)
    @GetMapping("/recruitment-process/{id}")
    public Iterable<ApplicationResponse> getApplicationsByRecruitmentProcessId(@PathVariable Long id) {
        return applicationService.getApplicationsByRecruitmentProcessId(id);
    }

    @Operation(summary = "Update applicant application phase")
    @Transactional
    @PutMapping("/update-phase/{applicationId}")
    public ResponseEntity<ApplicationResponse> updateApplication(@PathVariable Long applicationId, @RequestParam("newPhaseId") Long newPhaseId) {
        Application application = applicationService.updateApplicationPhase(applicationId, newPhaseId);
        return ResponseEntity.status(HttpStatus.OK).body(modelMapper.map(application, ApplicationResponse.class));
    }

    @Operation(summary = "Deactivate applicant application")
    @Transactional
    @PutMapping("/deactivate/{id}")
    public ResponseEntity<ApplicationResponse> deactivateApplication(@PathVariable Long id) {
        Application application = applicationService.deactivateApplication(id);
        return ResponseEntity.status(HttpStatus.OK).body(modelMapper.map(application, ApplicationResponse.class));
    }


    void ValidateApplicationRequest(ApplicationRequest applicationRequest) {
        if (applicationRequest.getApplicantId() == null ) {
            throw new IllegalArgumentException("Applicant is required");
        }
        if (applicationRequest.getRecruitmentProcessId() == null) {
            throw new IllegalArgumentException("Recruitment process is required");
        }
    }
}
