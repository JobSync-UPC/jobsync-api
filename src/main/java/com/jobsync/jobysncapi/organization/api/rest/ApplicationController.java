package com.jobsync.jobysncapi.organization.api.rest;

import com.jobsync.jobysncapi.organization.api.dto.request.ApplicationRequest;
import com.jobsync.jobysncapi.organization.api.dto.response.ApplicationResponse;
import com.jobsync.jobysncapi.organization.domain.model.entity.Application;
import com.jobsync.jobysncapi.organization.service.ApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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

    @Operation(summary = "Get application by id")
    @Transactional(readOnly = true)
    @GetMapping("/{id}")
    public ApplicationResponse getApplicationById(Long id) {
        return applicationService.getApplicationById(id);
    }
    @Operation(summary = "Create application")
    @Transactional
    @PostMapping("/create/{id}")
    public ResponseEntity<ApplicationResponse> createApplication(@PathVariable Long id,
                                                                 @RequestBody ApplicationRequest applicationRequest) {

        ValidateApplicationRequest(applicationRequest);

        Application createdApplication = applicationService.createApplication(applicationRequest, id);
        ApplicationResponse applicationResponse = modelMapper.map(createdApplication, ApplicationResponse.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(applicationResponse);
    }


    @Operation(summary = "Update application")
    @Transactional
    @PutMapping("/update/{id}")
    public ResponseEntity<ApplicationResponse> updateApplication(@PathVariable Long id,
                                                                 @RequestBody ApplicationRequest applicationRequest) {
        Application application = applicationService.updateApplication(id, applicationRequest);

        ApplicationResponse applicationResponse = modelMapper.map(application, ApplicationResponse.class);
        return ResponseEntity.ok(applicationResponse);
    }

    @Operation(summary = "Delete application")
    @Transactional
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteApplication(@PathVariable Long id) {
        applicationService.deleteApplication(id);
        return ResponseEntity.noContent().build();
    }


    void ValidateApplicationRequest(ApplicationRequest applicationRequest) {
        if (applicationRequest.getApplicant() == null ) {
            throw new IllegalArgumentException("Applicant is required");
        }
        if (applicationRequest.getRecruitmentProcess() == null) {
            throw new IllegalArgumentException("Recruitment process is required");
        }
    }
}
