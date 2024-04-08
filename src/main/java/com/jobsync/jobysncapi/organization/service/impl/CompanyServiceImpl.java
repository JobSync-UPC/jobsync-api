package com.jobsync.jobysncapi.organization.service.impl;


import com.jobsync.jobysncapi.organization.api.dto.request.CompanyRequest;
import com.jobsync.jobysncapi.organization.api.dto.response.CompanyResponse;
import com.jobsync.jobysncapi.organization.domain.model.entity.Company;
import com.jobsync.jobysncapi.organization.domain.persistence.CompanyRepository;
import com.jobsync.jobysncapi.organization.service.CompanyService;
import com.jobsync.jobysncapi.security.domain.model.entity.Recruiter;
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
    public Company updateCompany(Company company) {

        Company existingCompany = companyRepository.findById(company.getId()).orElse(null);

        if (existingCompany != null) {
            return companyRepository.save(existingCompany);
        } else {
            throw new GlobalExceptionHandler("Company","No existe la empresa a modificar");
        }

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

}
