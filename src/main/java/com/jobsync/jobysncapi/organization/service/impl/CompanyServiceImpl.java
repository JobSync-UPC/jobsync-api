package com.jobsync.jobysncapi.organization.service.impl;


import com.jobsync.jobysncapi.organization.api.dto.request.CompanyRequest;
import com.jobsync.jobysncapi.organization.api.dto.response.CompanyResponse;
import com.jobsync.jobysncapi.organization.domain.model.entity.Company;
import com.jobsync.jobysncapi.organization.domain.persistence.CompanyRepository;
import com.jobsync.jobysncapi.organization.service.CompanyService;
import com.jobsync.jobysncapi.recruiter.domain.model.entity.Recruiter;
import com.jobsync.jobysncapi.recruiter.domain.model.persistence.RecruiterRepository;
import com.jobsync.jobysncapi.shared.exception.GlobalExceptionHandler;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final RecruiterRepository recruiterRepository;

    private final ModelMapper modelMapper;


    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository, ModelMapper modelMapper, RecruiterRepository recruiterRepository) {
        this.companyRepository = companyRepository;
        this.modelMapper = modelMapper;
        this.recruiterRepository = recruiterRepository;
    }

    @Override
    public Company createCompany(CompanyRequest companyRequest, Long recruiterId) {

        Company company = modelMapper.map(companyRequest, Company.class);

        // Retrieve the recruiter owner by id
        Optional<Recruiter> optionalRecruiter = recruiterRepository.findById(recruiterId);
        if (optionalRecruiter.isEmpty()) {
            throw new IllegalArgumentException("Recruiter with id " + recruiterId + " not found");
        }

        Recruiter recruiter = optionalRecruiter.get();

        recruiter.setCompany(company);

        recruiterRepository.save(recruiter);

        company.setRecruiter_owner_id(recruiterId);

        // Create the recruiters list for company
        company.setRecruiters(List.of(recruiter));

        // Save the company
        return companyRepository.save(company);
    }

    @Override
    public Company addRecruiterToCompany(Long recruiterId, Long companyId) {
        // Retrieve the recruiter by id
        Optional<Recruiter> optionalRecruiter = recruiterRepository.findById(recruiterId);
        if (optionalRecruiter.isEmpty()) {
            throw new IllegalArgumentException("Recruiter with id " + recruiterId + " not found");
        }

        Recruiter recruiter = optionalRecruiter.get();

        // Retrieve the company by id
        Optional<Company> optionalCompany = companyRepository.findById(companyId);
        if (optionalCompany.isEmpty()) {
            throw new IllegalArgumentException("Company with id " + companyId + " not found");
        }

        // Check if recruiter already has a company
        if (recruiter.getCompany() != null) {
            throw new IllegalArgumentException("Recruiter with id already has a company");
        }

        Company company = optionalCompany.get();

        recruiter.setCompany(company);

        company.getRecruiters().add(recruiter);

        // Save the recruiter
        recruiterRepository.save(recruiter);

        // Save the company
        companyRepository.save(company);

        return company;
    }


    @Override
    public Company updateCompany(Long id, CompanyRequest companyRequest) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (optionalCompany.isEmpty()) {
            throw new GlobalExceptionHandler("Company","Company with id " + id + " not found");
        }

        Company existingCompany = optionalCompany.get();

        existingCompany.setName(companyRequest.getName());
        existingCompany.setDescription(companyRequest.getDescription());
        existingCompany.setCountry(companyRequest.getCountry());
        existingCompany.setAddress(companyRequest.getAddress());
        existingCompany.setLogoUrl(companyRequest.getLogoUrl());
        existingCompany.setWebsite(companyRequest.getWebsite());
        existingCompany.setIndustry(companyRequest.getIndustry());

        return companyRepository.save(existingCompany);
    }


    @Override
    public void deleteCompany(Long companyId) {

        companyRepository.deleteById(companyId);

        // for each recruiter in the company, set the company to null
        Iterable<Recruiter> recruiters = recruiterRepository.findAllByCompanyId(companyId);
        for (Recruiter recruiter : recruiters) {
            recruiter.setCompany(null);
            recruiterRepository.save(recruiter);
        }
    }

    @Override
    public Iterable<CompanyResponse> getAllCompanies() {
        Iterable<Company> companies = companyRepository.findAll();
        Type listType = new TypeToken<List<CompanyResponse>>() {}.getType();
        return modelMapper.map(companies, listType);
    }

    @Override
    public Company getCompanyById(Long companyId) {
        return companyRepository.findById(companyId).orElse(null);
    }

    @Override
    public Company disableCompany(Long companyId) {
        return companyRepository.findById(companyId)
                .map(company -> {
                    company.setEnabled(Boolean.FALSE);

                    // for each recruiter in the company, set the company to null
                    Iterable<Recruiter> recruiters = recruiterRepository.findAllByCompanyId(companyId);
                    for (Recruiter recruiter : recruiters) {
                        recruiter.setCompany(null);
                        recruiterRepository.save(recruiter);
                    }

                    return companyRepository.save(company);
                })
                .orElseThrow(() -> new RuntimeException("Company not found"));
    }


    @Override
    public Company enableCompany(Long companyId) {
        return companyRepository.findById(companyId)
                .map(company -> {
                    company.setEnabled(Boolean.TRUE);

                    // for each recruiter in the company, set the company to null
                    Iterable<Recruiter> recruiters = recruiterRepository.findAllByCompanyId(companyId);
                    for (Recruiter recruiter : recruiters) {
                        recruiter.setCompany(company);
                        recruiterRepository.save(recruiter);
                    }

                    return companyRepository.save(company);
                })
                .orElseThrow(() -> new RuntimeException("Company not found"));
    }


}
