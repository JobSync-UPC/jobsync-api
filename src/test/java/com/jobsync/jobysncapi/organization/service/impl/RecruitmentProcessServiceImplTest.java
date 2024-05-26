package com.jobsync.jobysncapi.organization.service.impl;

import com.jobsync.jobysncapi.organization.domain.model.entity.RecruitmentProcess;
import com.jobsync.jobysncapi.organization.domain.persistence.CompanyRepository;
import com.jobsync.jobysncapi.organization.domain.model.entity.Company;
import com.jobsync.jobysncapi.organization.domain.persistence.RecruitmentProcessRepository;
import com.jobsync.jobysncapi.organization.service.RecruitmentProcessService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RecruitmentProcessServiceImplTest {

    @Mock
    private RecruitmentProcessRepository recruitmentProcessRepository;

    @Mock
    private CompanyRepository companyRepository;

    @InjectMocks
    private RecruitmentProcessServiceImpl recruitmentProcessService;

    @Test
    public void testCreateRecruitmentProcess_Success() {
        Long companyId = 1L;
        Company expectedCompany = new Company();

        Mockito.when(companyRepository.findById(companyId)).thenReturn(Optional.of(expectedCompany));
        Mockito.when(recruitmentProcessRepository.save(Mockito.any(RecruitmentProcess.class))).thenReturn(new RecruitmentProcess());

        RecruitmentProcess createdProcess = recruitmentProcessService.createRecruitmentProcess(companyId);

        assertNotNull(createdProcess);
    }

    @Test
    public void testUpdateRecruitmentProcess() {
        Long recruitmentProcessId = 1L;
        RecruitmentProcess existingProcess = new RecruitmentProcess();
        existingProcess.setId(recruitmentProcessId);
        existingProcess.setEnabled(false);

        Mockito.when(recruitmentProcessRepository.findById(recruitmentProcessId)).thenReturn(Optional.of(existingProcess));
        Mockito.when(recruitmentProcessRepository.save(existingProcess)).thenReturn(existingProcess);

        RecruitmentProcess updatedProcess = recruitmentProcessService.updateRecruitmentProcess(recruitmentProcessId);

        assertTrue(updatedProcess.getEnabled());
    }


    @Test
    public void testGetRecruitmentProcessById() {
        Long recruitmentProcessId = 1L;
        RecruitmentProcess expectedProcess = new RecruitmentProcess();

        Mockito.when(recruitmentProcessRepository.findById(recruitmentProcessId)).thenReturn(Optional.of(expectedProcess));

        RecruitmentProcess actualProcess = recruitmentProcessService.getRecruitmentProcessById(recruitmentProcessId);

        assertEquals(actualProcess,expectedProcess);
    }
}