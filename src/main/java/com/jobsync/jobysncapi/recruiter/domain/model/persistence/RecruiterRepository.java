package com.jobsync.jobysncapi.recruiter.domain.model.persistence;

import com.jobsync.jobysncapi.recruiter.domain.model.entity.Recruiter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RecruiterRepository extends JpaRepository<Recruiter, Long>{
    Optional<Recruiter> findByEmail(String email);
    List<Recruiter> findAll();
}
