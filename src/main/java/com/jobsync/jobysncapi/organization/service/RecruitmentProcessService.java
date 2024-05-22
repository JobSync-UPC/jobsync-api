package com.jobsync.jobysncapi.organization.service;

import com.jobsync.jobysncapi.organization.domain.model.entity.RecruitmentProcess;

import java.util.List;

public interface RecruitmentProcessService {

    RecruitmentProcess createRecruitmentProcess(Long companyId);
    RecruitmentProcess updateRecruitmentProcess(Long recruitmentProcessId);
    void deleteRecruitmentProcess(Long recruitmentProcessId);
    RecruitmentProcess getRecruitmentProcessById(Long recruitmentProcessId);

    Iterable<RecruitmentProcess> getAllRecruitmentProcesses();

    Iterable<RecruitmentProcess> getRecruitmentProcessesByCompanyId(Long companyId);
}

