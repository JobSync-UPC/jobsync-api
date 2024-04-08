package com.jobsync.jobysncapi.organization.service;

import com.jobsync.jobysncapi.organization.api.dto.request.CompanyRequest;
import com.jobsync.jobysncapi.organization.api.dto.response.CompanyResponse;
import com.jobsync.jobysncapi.organization.domain.model.entity.Company;


public interface CompanyService {

    public abstract Company createCompany(CompanyRequest companyRequest, Long recruiterId);

    public abstract Company updateCompany(Company company);

    public abstract void deleteCompany(Long companyId);

    public Iterable<CompanyResponse> getAllCompanies();

    public abstract Company getCompanyById(Long companyId);




}
