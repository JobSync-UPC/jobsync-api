package com.jobsync.jobysncapi.organization.domain.persistence;

import com.jobsync.jobysncapi.organization.domain.model.entity.Company;
import com.jobsync.jobysncapi.organization.domain.model.entity.RecruitmentProcess;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface    RecruitmentProcessRepository extends JpaRepository<RecruitmentProcess, Long> {

    Iterable<RecruitmentProcess> findByCompanyId(Long companyId);

    boolean existsByIdAndCompanyId(Long recruitmentProcessId, Long companyId);
    Iterable<RecruitmentProcess> findAllByJobPostEnabledTrue();
}
