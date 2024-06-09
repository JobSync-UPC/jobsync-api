package com.jobsync.jobysncapi.organization.service.impl;

import com.jobsync.jobysncapi.organization.api.dto.request.RecruitmentPhaseRequest;
import com.jobsync.jobysncapi.organization.domain.model.entity.Company;
import com.jobsync.jobysncapi.organization.domain.model.entity.JobPost;
import com.jobsync.jobysncapi.organization.domain.model.entity.RecruitmentPhase;
import com.jobsync.jobysncapi.organization.domain.model.entity.RecruitmentProcess;
import com.jobsync.jobysncapi.organization.domain.persistence.CompanyRepository;
import com.jobsync.jobysncapi.organization.domain.persistence.JobPostRepository;
import com.jobsync.jobysncapi.organization.domain.persistence.RecruitmentPhaseRepository;
import com.jobsync.jobysncapi.organization.domain.persistence.RecruitmentProcessRepository;
import com.jobsync.jobysncapi.organization.service.JobPostService;
import com.jobsync.jobysncapi.organization.service.RecruitmentPhaseService;
import com.jobsync.jobysncapi.organization.service.RecruitmentProcessService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RecruitmentProcessServiceImpl implements RecruitmentProcessService {

    private final JobPostRepository jobPostRepository;

    private final RecruitmentProcessRepository recruitmentProcessRepository;

    private final RecruitmentPhaseService recruitmentPhaseService;

    private final CompanyRepository companyRepository;


    @Autowired
    RecruitmentProcessServiceImpl(RecruitmentProcessRepository recruitmentProcessRepository,
                                  RecruitmentPhaseService recruitmentPhaseService,
                                  CompanyRepository companyRepository,
                                  JobPostRepository jobPostRepository) {
        this.recruitmentProcessRepository = recruitmentProcessRepository;
        this.recruitmentPhaseService = recruitmentPhaseService;
        this.companyRepository = companyRepository;
        this.jobPostRepository = jobPostRepository;
    }

    @Override
    public RecruitmentProcess createRecruitmentProcess(Long companyId) {

        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new EntityNotFoundException("Company not found"));


        RecruitmentProcess recruitmentProcess = new RecruitmentProcess();
        recruitmentProcess.setCompany(company);
        recruitmentProcess.setCreated_date(new Date());
        recruitmentProcess.setEnabled(true);

        recruitmentProcess = recruitmentProcessRepository.save(recruitmentProcess);

        // Create default recruitment process phase

        RecruitmentPhaseRequest recruitmentPhaseRequest = new RecruitmentPhaseRequest();
        recruitmentPhaseRequest.setStartDate(new Date());
        recruitmentPhaseRequest.setEndDate(new Date());
        recruitmentPhaseRequest.setTitle("Applicants");
        recruitmentPhaseRequest.setDescription("New applicants");
        recruitmentPhaseRequest.setRecruitmentProcessId(recruitmentProcess.getId());

        recruitmentPhaseService.createRecruitmentPhase(recruitmentPhaseRequest);

        return recruitmentProcess;
    }

    @Override
    public RecruitmentProcess updateEnabledRecruitmentProcess(Long recruitmentProcessId) {

        RecruitmentProcess recruitmentProcess = recruitmentProcessRepository.findById(recruitmentProcessId)
                .orElseThrow(() -> new EntityNotFoundException("RecruitmentProcess not found"));

        recruitmentProcess.setEnabled(!recruitmentProcess.getEnabled());
        recruitmentProcess = recruitmentProcessRepository.save(recruitmentProcess);

        // Disable job post
        JobPost jobPost = jobPostRepository.findById(recruitmentProcess.getJobPost().getId()).get();

        jobPost.setEnabled(!jobPost.getEnabled());
        jobPostRepository.save(jobPost);

        return recruitmentProcess;
    }


    @Override
    public void deleteRecruitmentProcess(Long recruitmentProcessId) {

        if (!recruitmentProcessRepository.existsById(recruitmentProcessId)) {
            throw new EntityNotFoundException("RecruitmentProcess not found");
        }

        recruitmentProcessRepository.deleteById(recruitmentProcessId);
    }

    @Override
    public Iterable<RecruitmentProcess> getAllRecruitmentProcesses() {
        return recruitmentProcessRepository.findAll();
    }

    @Override
    public RecruitmentProcess getRecruitmentProcessById(Long recruitmentProcessId) {

        return recruitmentProcessRepository.findById(recruitmentProcessId)
                .orElseThrow(() -> new EntityNotFoundException("RecruitmentProcess not found"));
    }

    @Override
    public Iterable<RecruitmentProcess> getRecruitmentProcessesByCompanyId(Long companyId) {
        return recruitmentProcessRepository.findByCompanyId(companyId);
    }

    @Override
    public boolean isRecruitmentProcessFromCompany(Long recruitmentProcessId, Long companyId) {
        return recruitmentProcessRepository.existsByIdAndCompanyId(recruitmentProcessId, companyId);
    }

    @Override
    public Iterable<RecruitmentProcess> getAllActiveRecruitmentProcesses() {
        return recruitmentProcessRepository.findAllByJobPostEnabledTrue();
    }
}
