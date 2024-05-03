package com.jobsync.jobysncapi.recruiter.service;

import com.jobsync.jobysncapi.organization.domain.model.entity.Company;
import com.jobsync.jobysncapi.organization.domain.persistence.CompanyRepository;
import com.jobsync.jobysncapi.recruiter.domain.model.entity.Recruiter;
import com.jobsync.jobysncapi.recruiter.domain.model.persistence.RecruiterRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RecruiterServiceTest {

    @Mock
    private RecruiterRepository recruiterRepository;

    @Mock
    private CompanyRepository companyRepository;

    @InjectMocks
    private RecruiterService recruiterService;


    @Test
    public void testGetRecruiterById() {
        Long recruiterId = 1L;
        Recruiter expectedRecruiter = new Recruiter();
        expectedRecruiter.setId(recruiterId);

        Mockito.when(recruiterRepository.findById(recruiterId)).thenReturn(Optional.of(expectedRecruiter));

        Recruiter actualRecruiter = recruiterService.getRecruiterById(recruiterId);

        assertEquals(actualRecruiter,expectedRecruiter);
    }

    @Test
    public void testGetAllRecruiters() {
        List<Recruiter> expectedRecruiters = Arrays.asList(new Recruiter(), new Recruiter(),new Recruiter() );

        Mockito.when(recruiterRepository.findAll()).thenReturn(expectedRecruiters);

        Iterable<Recruiter> actualRecruiters = recruiterService.getAllRecruiters();

        assertEquals(actualRecruiters,expectedRecruiters);
    }
    @Test
    public void testHasCompany_True() {
        Long recruiterId = 1L;
        Recruiter recruiter = new Recruiter();
        recruiter.setId(recruiterId);
        recruiter.setCompany(new Company());

        Mockito.when(recruiterRepository.findById(recruiterId)).thenReturn(Optional.of(recruiter));

        boolean hasCompany = recruiterService.hasCompany(recruiterId);

        assertTrue(hasCompany);
    }

    @Test
    public void testFindByEmail_Found() {
        String email = "test@email.com";
        Recruiter expectedRecruiter = new Recruiter();

        Mockito.when(recruiterRepository.findByEmail(email)).thenReturn(Optional.of(expectedRecruiter));

        Recruiter actualRecruiter = recruiterService.findByEmail(email);

        assertEquals(actualRecruiter,expectedRecruiter);
    }


    @Test
    public void testLeaveCompany_Success() {
        Long recruiterId = 1L;
        Recruiter recruiter = new Recruiter();
        Company company = new Company();
        company.setRecruiters(new ArrayList<>());
        company.getRecruiters().add(recruiter);
        recruiter.setCompany(company);

        Mockito.when(recruiterRepository.findById(recruiterId)).thenReturn(Optional.of(recruiter));

        recruiterService.leaveCompany(recruiterId);

        assertNull(recruiter.getCompany());
    }
}