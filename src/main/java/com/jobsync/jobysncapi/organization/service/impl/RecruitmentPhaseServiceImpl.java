package com.jobsync.jobysncapi.organization.service.impl;

import com.jobsync.jobysncapi.organization.api.dto.request.RecruitmentPhaseRequest;
import com.jobsync.jobysncapi.organization.api.dto.response.RecruitmentPhaseResponse;
import com.jobsync.jobysncapi.organization.domain.model.entity.RecruitmentPhase;
import com.jobsync.jobysncapi.organization.domain.persistence.RecruitmentPhaseRepository;
import com.jobsync.jobysncapi.organization.service.RecruitmentPhaseService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Service
public class RecruitmentPhaseServiceImpl implements RecruitmentPhaseService {

    private final RecruitmentPhaseRepository recruitmentPhaseRepository;

    private final ModelMapper modelMapper;

    public RecruitmentPhaseServiceImpl(RecruitmentPhaseRepository recruitmentPhaseRepository, ModelMapper modelMapper) {
        this.recruitmentPhaseRepository = recruitmentPhaseRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Iterable<RecruitmentPhase> getAllRecruitmentPhases(){
        return recruitmentPhaseRepository.findAll();
    }

    @Override
    public RecruitmentPhase getRecruitmentPhaseById(Long recruitmentPhaseId){
        return recruitmentPhaseRepository.findById(recruitmentPhaseId).get();
    }

    @Override
    public RecruitmentPhase createRecruitmentPhase(RecruitmentPhaseRequest recruitmentPhaseRequest){
        RecruitmentPhase recruitmentPhase = modelMapper.map(recruitmentPhaseRequest, RecruitmentPhase.class);
        return recruitmentPhaseRepository.save(recruitmentPhase);
    }

    @Override
    public RecruitmentPhase updateRecruitmentPhase(Long recruitmentPhaseId, RecruitmentPhaseRequest recruitmentPhaseRequest){
        Optional<RecruitmentPhase> optionalRecruitmentPhase = recruitmentPhaseRepository.findById(recruitmentPhaseId);
        if (optionalRecruitmentPhase.isEmpty()) {
            throw new IllegalArgumentException("Recruitment Phase with id " + recruitmentPhaseId + " not found");
        }
        RecruitmentPhase recruitmentPhase = optionalRecruitmentPhase.get();
        modelMapper.map(recruitmentPhaseRequest, recruitmentPhase);
        return recruitmentPhaseRepository.save(recruitmentPhase);
    }

    @Override
    public void deleteRecruitmentPhase(Long recruitmentPhaseId){
        recruitmentPhaseRepository.deleteById(recruitmentPhaseId);
    }

}
