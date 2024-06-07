package com.jobsync.jobysncapi.organization.domain.persistence;

import com.jobsync.jobysncapi.organization.domain.model.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    public abstract Application findByApplicantIdAndRecruitmentProcessId(Long applicantId, Long recruitmentProcessId);
    public abstract Iterable<Application> findAllByApplicantId(Long applicantId);
    public abstract Iterable<Application> findAllByRecruitmentProcessId(Long recruitmentProcessId);
    public abstract Iterable<Application> findApplicationByCurrentApplicationPhase(Long currentApplicationPhase);
}
