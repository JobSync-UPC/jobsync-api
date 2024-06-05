package com.jobsync.jobysncapi.organization.service.impl;

import com.jobsync.jobysncapi.applicant.model.entity.Applicant;
import com.jobsync.jobysncapi.applicant.model.persistence.ApplicantRepository;
import com.jobsync.jobysncapi.organization.api.dto.request.ApplicationRequest;
import com.jobsync.jobysncapi.organization.api.dto.response.ApplicationResponse;
import com.jobsync.jobysncapi.organization.api.dto.response.CompanyResponse;
import com.jobsync.jobysncapi.organization.domain.model.entity.Application;
import com.jobsync.jobysncapi.organization.domain.model.entity.Company;
import com.jobsync.jobysncapi.organization.domain.model.entity.RecruitmentProcess;
import com.jobsync.jobysncapi.organization.domain.persistence.ApplicationRepository;
import com.jobsync.jobysncapi.organization.domain.persistence.RecruitmentProcessRepository;
import com.jobsync.jobysncapi.organization.service.ApplicationService;
import com.jobsync.jobysncapi.recruiter.domain.model.entity.Recruiter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;

    private final RecruitmentProcessRepository recruitmentProcessRepository;

    private final ApplicantRepository applicantRepository;

    private final ModelMapper modelMapper;

    public ApplicationServiceImpl(ApplicationRepository applicationRepository,
                                  RecruitmentProcessRepository recruitmentProcessRepository,
                                  ApplicantRepository applicantRepository,
                                  ModelMapper modelMapper) {
        this.applicationRepository = applicationRepository;
        this.recruitmentProcessRepository = recruitmentProcessRepository;
        this.applicantRepository = applicantRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Iterable<ApplicationResponse> getAllApplications() {
        Iterable<Application> applications = applicationRepository.findAll();
        Type listType = new TypeToken<List<ApplicationResponse>>() {}.getType();
        return modelMapper.map(applications, listType);
    }

    @Override
    public ApplicationResponse getApplicationById(Long applicationId) {
        return null;
    }

    @Override
    public Application applyForJob(ApplicationRequest applicationRequest) {

        // Verify if applicant has already applied for the job
        Application existingApplication = applicationRepository
                .findByApplicantIdAndRecruitmentProcessId(applicationRequest.getApplicantId(), applicationRequest.getRecruitmentProcessId());
        if (existingApplication != null) {
            throw new IllegalArgumentException("Applicant with id " + applicationRequest.getApplicantId() + " has already applied for the job with id " + applicationRequest.getRecruitmentProcessId());
        }

        Application application = new Application();

        // Verify if the recruitment process exists
        Optional<RecruitmentProcess> optionalRecruitmentProcess = recruitmentProcessRepository.findById(applicationRequest.getRecruitmentProcessId());
        if (optionalRecruitmentProcess.isEmpty()) {
            throw new IllegalArgumentException("Recruitment process with id " + applicationRequest.getRecruitmentProcessId() + " not found");
        }

        // Verify if the applicant exists
        Optional<Applicant> optionalApplicant = applicantRepository.findById(applicationRequest.getApplicantId());
        if (optionalApplicant.isEmpty()) {
            throw new IllegalArgumentException("Applicant with id " + applicationRequest.getApplicantId() + " not found");
        }


        application.setRecruitmentProcess(optionalRecruitmentProcess.get());
        application.setApplicant(optionalApplicant.get());
        application.setApplication_date(new Date());
        application.setIs_active(true);
        application.setCurrent_application_phase(optionalRecruitmentProcess.get().getRecruitmentPhases().getFirst().getId()); // First phase of the recruitment process

        return applicationRepository.save(application);
    }

    @Override
    public Iterable<ApplicationResponse> getApplicationsByApplicantId(Long id) {
        Iterable<Application> applications = applicationRepository.findAllByApplicantId(id);
        Type listType = new TypeToken<List<ApplicationResponse>>() {}.getType();
        return modelMapper.map(applications, listType);
    }

    @Override
    public Iterable<ApplicationResponse> getApplicationsByRecruitmentProcessId(Long id) {
        Iterable<Application> applications = applicationRepository.findAllByRecruitmentProcessId(id);
        Type listType = new TypeToken<List<ApplicationResponse>>() {}.getType();
        return modelMapper.map(applications, listType);
    }

    @Override
    public Application updateApplicationPhase(Long applicationId, Long newPhaseId) {
        return applicationRepository.findById(applicationId)
                .map(application -> {
                    // Verify if the new phase is valid
                    application.getRecruitmentProcess().getRecruitmentPhases().stream()
                            .filter(phase -> phase.getId().equals(newPhaseId))
                            .findFirst()
                            .orElseThrow(() -> new IllegalArgumentException("Recruitment phase with id " + newPhaseId + " not found"));

                    application.setCurrent_application_phase(newPhaseId);

                    return applicationRepository.save(application);
                })
                .orElseThrow(() -> new RuntimeException("Application not found"));
    }

    @Override
    public Application deactivateApplication(Long applicationId) {
        return applicationRepository.findById(applicationId)
                .map(application -> {
                    application.setIs_active(false);

                    return applicationRepository.save(application);
                })
                .orElseThrow(() -> new RuntimeException("Application not found"));
    }
}
