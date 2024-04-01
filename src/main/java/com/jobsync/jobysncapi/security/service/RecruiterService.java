package com.jobsync.jobysncapi.security.service;

import com.jobsync.jobysncapi.security.domain.model.entity.Recruiter;
import com.jobsync.jobysncapi.security.domain.persistence.RecruiterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecruiterService {

    private final RecruiterRepository recruiterRepository;

    public Optional<Recruiter> getRecruiterById(Long id) {
        return recruiterRepository.findById(id);
    }

    public List<Recruiter> getAllRecruiters(Long id) {
        return recruiterRepository.findAll();
    }
}
