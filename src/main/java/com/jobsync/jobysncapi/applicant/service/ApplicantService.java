package com.jobsync.jobysncapi.applicant.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jobsync.jobysncapi.applicant.model.entity.Applicant;
import com.jobsync.jobysncapi.applicant.model.persistence.ApplicantRepository;
import com.jobsync.jobysncapi.applicant.service.dto.UpdateApplicantProfileDto;
import com.jobsync.jobysncapi.security.domain.model.entity.User;
import com.jobsync.jobysncapi.shared.clients.StorageClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApplicantService {

    private final ApplicantRepository applicantRepository;

    private final StorageClient storageClient;

     public Optional<Applicant> getApplicantById(Long id) {
         return applicantRepository.findById(id);
     }

    public List<Applicant> getAllApplicants() {
        return applicantRepository.findAll();
    }

    public Applicant updateApplicant(Long id, UpdateApplicantProfileDto updatedApplicant) {
        return applicantRepository.findById(id)
                .map(applicant -> {
                    applicant.setPortfolioUrl(updatedApplicant.getPortfolioUrl());
                    applicant.setLinkedInUrl(updatedApplicant.getLinkedInUrl());

                    return applicantRepository.save(applicant);
                })
                .orElseThrow(() -> new RuntimeException("Applicant not found"));
    }

    public Applicant updateApplicantCV(Long id, MultipartFile file) {
        String jsonResponse = storageClient.uploadImage(file);

        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode jsonNode = mapper.readTree(jsonResponse);
            String cvUrl = jsonNode.get("azureUrl").asText();

            return applicantRepository.findById(id)
                    .map(applicant -> {
                        applicant.setCvUrl(cvUrl);
                        return applicantRepository.save(applicant);
                    })
                    .orElseThrow(() -> new RuntimeException("Applicant not found"));
        } catch (Exception e) {
            throw new RuntimeException("Error uploading CV");
        }
    }
}
