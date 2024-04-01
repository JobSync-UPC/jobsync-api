package com.jobsync.jobysncapi.security.service;

import com.jobsync.jobysncapi.security.domain.model.entity.Applicant;
import com.jobsync.jobysncapi.security.domain.model.entity.User;
import com.jobsync.jobysncapi.security.domain.persistence.ApplicantRepository;
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

    public List<Applicant> getAllApplicants(Long id) {
        return applicantRepository.findAll();
    }
}
