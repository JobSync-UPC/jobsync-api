package com.jobsync.jobysncapi.applicant.model.persistence;

import com.jobsync.jobysncapi.applicant.model.entity.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplicantRepository extends JpaRepository<Applicant, Long>{
    Optional<Applicant> findByEmail(String email);
    Boolean existsByEmail(String email);
}
