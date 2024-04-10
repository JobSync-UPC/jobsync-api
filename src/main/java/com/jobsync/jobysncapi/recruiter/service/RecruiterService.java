package com.jobsync.jobysncapi.recruiter.service;

import com.jobsync.jobysncapi.organization.domain.model.entity.Company;
import com.jobsync.jobysncapi.organization.domain.persistence.CompanyRepository;
import com.jobsync.jobysncapi.recruiter.domain.model.entity.Recruiter;
import com.jobsync.jobysncapi.recruiter.domain.model.persistence.RecruiterRepository;
import com.jobsync.jobysncapi.shared.exception.GlobalExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecruiterService {

    private final RecruiterRepository recruiterRepository;
    private final CompanyRepository companyRepository;

    public Recruiter getRecruiterById(Long id) {
        return recruiterRepository.findById(id
        ).orElseThrow(() -> new GlobalExceptionHandler("Recruiter","Recruiter not found"));
    }

    public Iterable<Recruiter> getAllRecruiters() {
        return recruiterRepository.findAll();
    }

    public Boolean hasCompany(Long recruiterId) {
        Recruiter recruiter = recruiterRepository.findById(recruiterId
        ).orElseThrow(() -> new GlobalExceptionHandler("Recruiter","Recruiter not found"));

        return recruiter.getCompany() != null;
    }

    public Recruiter findByEmail(String email) {
        return recruiterRepository.findByEmail(email).orElse(null);
    }

    public void addRecruiterToCompany(Long recruiterId, Long companyId) {
        Recruiter recruiter = recruiterRepository.findById(recruiterId
        ).orElseThrow(() -> new GlobalExceptionHandler("Recruiter","Recruiter not found"));

        Company company = companyRepository.findById(companyId
        ).orElseThrow(() -> new GlobalExceptionHandler("Company","Company not found"));

        if (recruiter.getCompany() != null) {
            throw new GlobalExceptionHandler("Recruiter","Recruiter already has a company");
        }
        else {
            recruiter.setCompany(company);
            recruiterRepository.save(recruiter);
        }
    }
}
