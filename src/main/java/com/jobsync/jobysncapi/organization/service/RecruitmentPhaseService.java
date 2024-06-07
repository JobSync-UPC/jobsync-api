package com.jobsync.jobysncapi.organization.service;

import com.jobsync.jobysncapi.organization.api.dto.request.RecruitmentPhaseRequest;
import com.jobsync.jobysncapi.organization.api.dto.response.RecruitmentPhaseResponse;
import com.jobsync.jobysncapi.organization.domain.model.entity.RecruitmentPhase;

import java.util.Optional;

public interface RecruitmentPhaseService {


    public abstract Iterable<RecruitmentPhase> getAllRecruitmentPhases();
    public abstract RecruitmentPhase getRecruitmentPhaseById(Long recruitmentPhaseId);

    public abstract RecruitmentPhase createRecruitmentPhase(RecruitmentPhaseRequest recruitmentPhaseRequest);

    public abstract RecruitmentPhase updateRecruitmentPhase(Long recruitmentPhaseId, RecruitmentPhaseRequest recruitmentPhaseRequest);

    public abstract void deleteRecruitmentPhase(Long recruitmentPhaseId);
}
