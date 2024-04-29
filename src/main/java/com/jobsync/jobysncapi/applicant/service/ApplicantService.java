package com.jobsync.jobysncapi.applicant.service;

import com.jobsync.jobysncapi.applicant.model.entity.Applicant;
import com.jobsync.jobysncapi.applicant.model.persistence.ApplicantRepository;
import com.jobsync.jobysncapi.applicant.service.dto.UpdateApplicantProfileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApplicantService {

    private final ApplicantRepository applicantRepository;

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
                    applicant.setCvUrl(updatedApplicant.getCvUrl());

                    return applicantRepository.save(applicant);
                })
                .orElseThrow(() -> new RuntimeException("Applicant not found"));
    }
}
