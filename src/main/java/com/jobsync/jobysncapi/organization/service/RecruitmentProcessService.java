package com.jobsync.jobysncapi.organization.service;

import com.jobsync.jobysncapi.organization.domain.model.entity.RecruitmentProcess;

public interface RecruitmentProcessService {

    RecruitmentProcess createRecruitmentProcess(Long companyId);
    RecruitmentProcess updateRecruitmentProcess(Long recruitmentProcessId);
    void deleteRecruitmentProcess(Long recruitmentProcessId);
    RecruitmentProcess getRecruitmentProcessById(Long recruitmentProcessId);

    Iterable<RecruitmentProcess> getAllRecruitmentProcesses();
}

