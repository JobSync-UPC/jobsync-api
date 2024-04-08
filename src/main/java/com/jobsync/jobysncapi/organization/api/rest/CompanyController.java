package com.jobsync.jobysncapi.organization.api.rest;


import com.jobsync.jobysncapi.organization.api.dto.request.CompanyRequest;
import com.jobsync.jobysncapi.organization.api.dto.response.CompanyResponse;
import com.jobsync.jobysncapi.organization.domain.model.entity.Company;
import com.jobsync.jobysncapi.organization.domain.persistence.CompanyRepository;
import com.jobsync.jobysncapi.organization.service.CompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Companies Controller")
@RestController
@RequestMapping("/api/v1/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    private final CompanyRepository companyRepository;
    private final ModelMapper modelMapper;

    public CompanyController(CompanyRepository companyRepository, ModelMapper modelMapper) {
        this.companyRepository = companyRepository;
        this.modelMapper = modelMapper;
    }

    @Operation(summary = "Get all companies")
    @Transactional(readOnly = true)
    @GetMapping("/")
    public Iterable<CompanyResponse> getAllCompanies() {
        return companyService.getAllCompanies();
    }

    @Operation(summary = "Get company by id")
    @Transactional(readOnly = true)
    @GetMapping("/{id}")
    public Company getCompanyById(Long id) {
        return companyService.getCompanyById(id);
    }

    @Operation(summary = "Create company")
    @Transactional
    @PostMapping("/create/{recruiterId}")
    public ResponseEntity<CompanyResponse> createCompany(@PathVariable("recruiterId") Long recruiterId,
                                                         @RequestBody CompanyRequest companyRequest) {
        //ValidateCompaniesRequest(companyRequest);

        Company createdCompany = companyService.createCompany(companyRequest , recruiterId);
        CompanyResponse companyResponse = modelMapper.map(createdCompany, CompanyResponse.class);
        return new ResponseEntity<>(companyResponse, HttpStatus.CREATED);
    }
}
