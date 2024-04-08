package com.jobsync.jobysncapi.organization.service.impl;


import com.jobsync.jobysncapi.organization.api.dto.request.CompanyRequest;
import com.jobsync.jobysncapi.organization.api.dto.response.CompanyResponse;
import com.jobsync.jobysncapi.organization.domain.model.entity.Company;
import com.jobsync.jobysncapi.organization.domain.persistence.CompanyRepository;
import com.jobsync.jobysncapi.organization.service.CompanyService;
import com.jobsync.jobysncapi.security.domain.model.entity.Recruiter;
import com.jobsync.jobysncapi.security.domain.model.entity.User;
import com.jobsync.jobysncapi.security.domain.persistence.RecruiterRepository;
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

        company.setRecruiter_owner_id(recruiterId);

        return companyRepository.save(company);
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
                    return companyRepository.save(company);
                })
                .orElseThrow(() -> new RuntimeException("Company not found"));
    }


    @Override
    public Company enableCompany(Long companyId) {
        return companyRepository.findById(companyId)
                .map(company -> {
                    company.setEnabled(Boolean.TRUE);
                    return companyRepository.save(company);
                })
                .orElseThrow(() -> new RuntimeException("Company not found"));
    }


}
