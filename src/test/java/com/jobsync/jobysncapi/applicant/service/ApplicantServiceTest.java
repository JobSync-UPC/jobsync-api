package com.jobsync.jobysncapi.applicant.service;

import com.jobsync.jobysncapi.applicant.model.entity.Applicant;
import com.jobsync.jobysncapi.applicant.model.persistence.ApplicantRepository;
import com.jobsync.jobysncapi.applicant.service.dto.UpdateApplicantProfileDto;
import com.jobsync.jobysncapi.shared.clients.CloudinaryClient;
import com.jobsync.jobysncapi.shared.clients.StorageClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Null;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class ApplicantServiceTest {

    @Mock
    private ApplicantRepository applicantRepository;

    @Mock
    private StorageClient storageClient;

    @InjectMocks
    private ApplicantService applicantService;

    @Test
    public void testGetApplicantById_Found() {
        Long applicantId = 1L;
        Applicant expectedApplicant = new Applicant();
        expectedApplicant.setId(applicantId);

        Mockito.when(applicantRepository.findById(applicantId)).thenReturn(Optional.of(expectedApplicant));

        Optional<Applicant> actualApplicant = applicantService.getApplicantById(applicantId);

        assertNotNull(actualApplicant);
        assertEquals(actualApplicant.get(),expectedApplicant);
        Mockito.verify(applicantRepository).findById(applicantId);
    }

    @Test
    public void testGetAllApplicants() {
        List<Applicant> expectedApplicants = Arrays.asList(new Applicant(), new Applicant());

        Mockito.when(applicantRepository.findAll()).thenReturn(expectedApplicants);

        List<Applicant> actualApplicants = applicantService.getAllApplicants();

        assertEquals(actualApplicants,expectedApplicants);
        Mockito.verify(applicantRepository).findAll();
    }

    @Test
    public void testUpdateApplicant() {
        Long applicantId = 1L;
        UpdateApplicantProfileDto updateDto = new UpdateApplicantProfileDto();
        updateDto.setPortfolioUrl("newPortfolioUrl");
        updateDto.setLinkedInUrl("newLinkedInUrl");
        Applicant applicantToUpdate = new Applicant();
        applicantToUpdate.setId(applicantId);

        Mockito.when(applicantRepository.findById(applicantId)).thenReturn(Optional.of(applicantToUpdate));
        Mockito.when(applicantRepository.save(applicantToUpdate)).thenReturn(applicantToUpdate);

        Applicant updatedApplicant = applicantService.updateApplicant(applicantId, updateDto);

        assertEquals(updatedApplicant.getPortfolioUrl(),updateDto.getPortfolioUrl());
        assertEquals(updatedApplicant.getLinkedInUrl(),updateDto.getLinkedInUrl());
        Mockito.verify(applicantRepository).findById(applicantId);
        Mockito.verify(applicantRepository).save(applicantToUpdate);
    }

    @Test
    void testUpdateApplicantCV() {
        Long applicantId = 1L;
        MultipartFile mockFile = new MockMultipartFile("cv.pdf", new byte[0]);

        String jsonResponse = "{\"azureUrl\": \"https://example.com/cv.pdf\"}";

        Applicant existingApplicant = new Applicant();
        existingApplicant.setId(applicantId);

        Mockito.when(storageClient.uploadImage(mockFile)).thenReturn(jsonResponse);
        Mockito.when(applicantRepository.findById(applicantId)).thenReturn(Optional.of(existingApplicant));
        Mockito.when(applicantRepository.save(existingApplicant)).thenReturn(existingApplicant);

        Applicant result = applicantService.updateApplicantCV(applicantId, mockFile);

        assertEquals("https://example.com/cv.pdf", result.getCvUrl());
    }
}