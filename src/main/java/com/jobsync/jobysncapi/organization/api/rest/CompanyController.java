package com.jobsync.jobysncapi.organization.api.rest;


import com.jobsync.jobysncapi.organization.api.dto.request.CompanyRequest;
import com.jobsync.jobysncapi.organization.api.dto.response.CompanyResponse;
import com.jobsync.jobysncapi.organization.domain.model.entity.Company;
import com.jobsync.jobysncapi.organization.domain.persistence.CompanyRepository;
import com.jobsync.jobysncapi.organization.service.CompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Tag(name = "Companies Controller", description = "Create, read, update, and delete companies")
@RestController
@RequestMapping("/api/v1/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    private final CompanyRepository companyRepository;

    private final ModelMapper modelMapper;

    public CompanyController(CompanyRepository companyRepository, ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.companyRepository = companyRepository;
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
    public Company getCompanyById(@PathVariable Long id) {
        return companyService.getCompanyById(id);
    }

    @Operation(summary = "Create company")
    @Transactional
    @PostMapping("/create/{recruiterId}")
    public ResponseEntity<CompanyResponse> createCompany(@PathVariable("recruiterId") Long recruiterId,
                                                         @RequestBody CompanyRequest companyRequest) {
        ValidateCompaniesRequest(companyRequest);

        Company createdCompany = companyService.createCompany(companyRequest , recruiterId);
        CompanyResponse companyResponse = modelMapper.map(createdCompany, CompanyResponse.class);
        return new ResponseEntity<>(companyResponse, HttpStatus.CREATED);
    }

    @Operation(summary = "Update company by id")
    @Transactional
    @PutMapping("/update/{id}")
    public ResponseEntity<CompanyResponse> updateCompany(@PathVariable("id") Long id,
                                                         @RequestBody CompanyRequest companyRequest) {
        Company updatedCompany = companyService.updateCompany(id, companyRequest);

        CompanyResponse companyResponse = modelMapper.map(updatedCompany, CompanyResponse.class);
        return new ResponseEntity<>(companyResponse, HttpStatus.OK);
    }


    @Operation(summary = "Delete company by id")
    @Transactional
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteCompany(@PathVariable("id") Long id) {
        companyService.deleteCompany(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Disable company by id", responses = {
            @ApiResponse(description = "Company found and disabled",
                    responseCode = "201",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Company.class)))
    })
    @PutMapping("/disable/{id}")
    public Company disableCompany(@PathVariable Long id) {
        return companyService.disableCompany(id);
    }

    @Operation(summary = "Enable company by id", responses = {
            @ApiResponse(description = "Company found and enabled",
                    responseCode = "201",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Company.class)))
    })
    @PutMapping("/enable/{id}")
    public Company enableCompany(@PathVariable Long id) {
        return companyService.enableCompany(id);
    }


    void ValidateCompaniesRequest(CompanyRequest companyRequest) {
        if (companyRequest.getName() == null || companyRequest.getName().isEmpty()) {
            throw new IllegalArgumentException("Company name is required");
        }
        if (companyRequest.getCountry() == null || companyRequest.getCountry().isEmpty()) {
            throw new IllegalArgumentException("Company country is required");
        }
        if (companyRequest.getAddress() == null || companyRequest.getAddress().isEmpty()) {
            throw new IllegalArgumentException("Company address is required");
        }
    }
}
