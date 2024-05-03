package com.jobsync.jobysncapi.organization.service.impl;

import com.jobsync.jobysncapi.organization.api.dto.request.CompanyRequest;
import com.jobsync.jobysncapi.organization.domain.persistence.CompanyRepository;
import com.jobsync.jobysncapi.organization.domain.model.entity.Company;
import com.jobsync.jobysncapi.recruiter.domain.model.persistence.RecruiterRepository;
import com.jobsync.jobysncapi.recruiter.domain.model.entity.Recruiter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CompanyServiceImplTest {

    @Mock
    private CompanyRepository companyRepository;

    @Mock
    private ModelMapper modelMapper;
    @Mock
    private RecruiterRepository recruiterRepository;

    @InjectMocks
    private CompanyServiceImpl companyService;

    @Test
    void createCompany() {

        CompanyRequest companyRequest = new CompanyRequest();

        Company company = new Company();
        company.setRecruiters(new ArrayList<>()); // Inicializa la lista de reclutadores

        when(modelMapper.map(any(), any())).thenReturn(company);
        when(recruiterRepository.findById(anyLong())).thenReturn(Optional.of(new Recruiter()));

        when(companyRepository.save(any())).thenReturn(company);

        Company createdCompany = companyService.createCompany(companyRequest, 1L);

        assertEquals(company.getName(), createdCompany.getName());
        assertEquals(company.getAddress(), createdCompany.getAddress());
        assertEquals(company.getCountry(), createdCompany.getCountry());
        assertEquals(company.getWebsite(), createdCompany.getWebsite());
    }

    @Test
    void addRecruiterToCompany() {
        Long recruiterId = 1L;
        Long companyId = 1L;

        Recruiter recruiter = new Recruiter();
        Company company = new Company();
        company.setRecruiters(new ArrayList<>());

        when(recruiterRepository.findById(anyLong())).thenReturn(Optional.of(recruiter));
        when(companyRepository.findById(anyLong())).thenReturn(Optional.of(company));

        Company updatedCompany = companyService.addRecruiterToCompany(recruiterId, companyId);

        assertNotNull(updatedCompany);
        assertTrue(updatedCompany.getRecruiters().contains(recruiter));
    }

    @Test
    void updateCompany() {
        Long companyId = 1L;
        CompanyRequest companyRequest = new CompanyRequest();
        companyRequest.setName("Updated Company Name");
        companyRequest.setAddress("Updated Company Address");
        companyRequest.setCountry("Updated Company Country");
        companyRequest.setWebsite("Updated Company Website");

        when(companyRepository.findById(anyLong())).thenReturn(Optional.of(new Company()));
        when(companyRepository.save(any(Company.class))).thenAnswer(i -> i.getArguments()[0]);

        Company updatedCompany = companyService.updateCompany(companyId, companyRequest);

        assertEquals(companyRequest.getName(), updatedCompany.getName());
        assertEquals(companyRequest.getAddress(), updatedCompany.getAddress());
        assertEquals(companyRequest.getCountry(), updatedCompany.getCountry());
        assertEquals(companyRequest.getWebsite(), updatedCompany.getWebsite());
    }

    @Test
    void deleteCompany() {
        Long companyId = 1L;

        doNothing().when(companyRepository).deleteById(anyLong());

        assertDoesNotThrow(() -> companyService.deleteCompany(companyId));
    }
    @Test
    void getCompanyById() {
        Long companyId = 1L;
        Company company = new Company();
        company.setName("Company Name");
        company.setAddress("Company Address");
        company.setCountry("Company Country");
        company.setWebsite("Company Website");

        when(companyRepository.findById(anyLong())).thenReturn(Optional.of(company));

        Company retrievedCompany = companyService.getCompanyById(companyId);

        assertEquals(company.getName(), retrievedCompany.getName());
        assertEquals(company.getAddress(), retrievedCompany.getAddress());
        assertEquals(company.getCountry(), retrievedCompany.getCountry());
        assertEquals(company.getWebsite(), retrievedCompany.getWebsite());
    }
}