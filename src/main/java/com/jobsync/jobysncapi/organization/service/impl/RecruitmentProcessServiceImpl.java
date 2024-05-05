package com.jobsync.jobysncapi.organization.service.impl;

import com.jobsync.jobysncapi.organization.domain.model.entity.Company;
import com.jobsync.jobysncapi.organization.domain.model.entity.JobPost;
import com.jobsync.jobysncapi.organization.domain.model.entity.RecruitmentProcess;
import com.jobsync.jobysncapi.organization.domain.persistence.CompanyRepository;
import com.jobsync.jobysncapi.organization.domain.persistence.JobPostRepository;
import com.jobsync.jobysncapi.organization.domain.persistence.RecruitmentProcessRepository;
import com.jobsync.jobysncapi.organization.service.RecruitmentProcessService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RecruitmentProcessServiceImpl implements RecruitmentProcessService {



    private final RecruitmentProcessRepository recruitmentProcessRepository;

    private final CompanyRepository companyRepository;


    @Autowired
    RecruitmentProcessServiceImpl(RecruitmentProcessRepository recruitmentProcessRepository,
                                  CompanyRepository companyRepository) {
        this.recruitmentProcessRepository = recruitmentProcessRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    public RecruitmentProcess createRecruitmentProcess(Long companyId) {

        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new EntityNotFoundException("Company not found"));


        RecruitmentProcess recruitmentProcess = new RecruitmentProcess();
        recruitmentProcess.setCompany(company);
        recruitmentProcess.setCreated_date(new Date());
        recruitmentProcess = recruitmentProcessRepository.save(recruitmentProcess);

        return recruitmentProcess;

    }

    @Override
    public RecruitmentProcess updateRecruitmentProcess(Long recruitmentProcessId) {

        RecruitmentProcess recruitmentProcess = recruitmentProcessRepository.findById(recruitmentProcessId)
                .orElseThrow(() -> new EntityNotFoundException("RecruitmentProcess not found"));

        recruitmentProcess.setEnabled(!recruitmentProcess.getEnabled());
        recruitmentProcess = recruitmentProcessRepository.save(recruitmentProcess);

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
    public List<RecruitmentProcess> getRecruitmentProcessesByCompanyId(Long companyId) {
        return recruitmentProcessRepository.findByCompanyId(companyId);
    }
}
