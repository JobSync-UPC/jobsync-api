package com.jobsync.jobysncapi.organization.domain.persistence;

import com.jobsync.jobysncapi.organization.domain.model.entity.RecruitmentPhase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecruitmentPhaseRepository extends JpaRepository<RecruitmentPhase, Long> {
}
