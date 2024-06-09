package com.jobsync.jobysncapi.organization.service.impl;

import com.jobsync.jobysncapi.organization.api.dto.request.RecruitmentPhaseRequest;
import com.jobsync.jobysncapi.organization.api.dto.response.RecruitmentPhaseResponse;
import com.jobsync.jobysncapi.organization.domain.model.entity.Application;
import com.jobsync.jobysncapi.organization.domain.model.entity.JobPost;
import com.jobsync.jobysncapi.organization.domain.model.entity.RecruitmentPhase;
import com.jobsync.jobysncapi.organization.domain.model.entity.RecruitmentProcess;
import com.jobsync.jobysncapi.organization.domain.persistence.ApplicationRepository;
import com.jobsync.jobysncapi.organization.domain.persistence.RecruitmentPhaseRepository;
import com.jobsync.jobysncapi.organization.domain.persistence.RecruitmentProcessRepository;
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
    private final RecruitmentProcessRepository recruitmentProcessRepository;
    private final ApplicationRepository applicationRepository;

    public RecruitmentPhaseServiceImpl(RecruitmentPhaseRepository recruitmentPhaseRepository, ModelMapper modelMapper, RecruitmentProcessRepository recruitmentProcessRepository, ApplicationRepository applicationRepository) {
        this.recruitmentPhaseRepository = recruitmentPhaseRepository;
        this.modelMapper = modelMapper;
        this.recruitmentProcessRepository = recruitmentProcessRepository;
        this.applicationRepository = applicationRepository;
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
        RecruitmentProcess recruitmentProcess = recruitmentProcessRepository.findById(recruitmentPhaseRequest.getRecruitmentProcessId()).get();

        RecruitmentPhase newRecruitmentPhase = RecruitmentPhase.builder()
                .start_date(recruitmentPhaseRequest.getStartDate())
                .end_date(recruitmentPhaseRequest.getEndDate())
                .title(recruitmentPhaseRequest.getTitle())
                .description(recruitmentPhaseRequest.getDescription())
                .recruitmentProcess(recruitmentProcess)
                .build();

        return recruitmentPhaseRepository.save(newRecruitmentPhase);
    }

    @Override
    public RecruitmentPhase updateRecruitmentPhase(Long recruitmentPhaseId, RecruitmentPhaseRequest recruitmentPhaseRequest){
        Optional<RecruitmentPhase> optionalRecruitmentPhase = recruitmentPhaseRepository.findById(recruitmentPhaseId);
        if (optionalRecruitmentPhase.isEmpty()) {
            throw new IllegalArgumentException("Recruitment Phase with id " + recruitmentPhaseId + " not found");
        }
        RecruitmentPhase recruitmentPhase = optionalRecruitmentPhase.get();

        recruitmentPhase.setTitle(recruitmentPhaseRequest.getTitle());
        recruitmentPhase.setDescription(recruitmentPhaseRequest.getDescription());

        return recruitmentPhaseRepository.save(recruitmentPhase);
    }

    @Override
    public void deleteRecruitmentPhase(Long recruitmentPhaseId){
        RecruitmentPhase recruitmentPhase = recruitmentPhaseRepository.findById(recruitmentPhaseId).get();

        Iterable<Application> applications = applicationRepository.findApplicationByCurrentRecruitmentPhase(recruitmentPhase);

        RecruitmentPhase firstRecruitmentPhase = recruitmentPhase.getRecruitmentProcess().getRecruitmentPhases().get(1);

        for (Application application : applications) {
            application.setCurrentRecruitmentPhase(firstRecruitmentPhase);
            applicationRepository.save(application);
        }

        recruitmentPhaseRepository.deleteById(recruitmentPhaseId);
    }

}
