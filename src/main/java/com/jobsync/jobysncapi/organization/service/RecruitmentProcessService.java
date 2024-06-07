package com.jobsync.jobysncapi.organization.service;

import com.jobsync.jobysncapi.organization.domain.model.entity.RecruitmentProcess;

import java.util.List;

public interface RecruitmentProcessService {

    public abstract RecruitmentProcess createRecruitmentProcess(Long companyId);
    public abstract RecruitmentProcess updateRecruitmentProcess(Long recruitmentProcessId);
    public abstract void deleteRecruitmentProcess(Long recruitmentProcessId);
    public abstract RecruitmentProcess getRecruitmentProcessById(Long recruitmentProcessId);

    public abstract Iterable<RecruitmentProcess> getAllRecruitmentProcesses();

    public abstract Iterable<RecruitmentProcess> getRecruitmentProcessesByCompanyId(Long companyId);
    public abstract boolean isRecruitmentProcessFromCompany(Long recruitmentProcessId, Long companyId);
}

