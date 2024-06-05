//package com.jobsync.jobysncapi.organization.service.impl;
//
//import com.jobsync.jobysncapi.organization.api.dto.request.ApplicationPhaseRequest;
//import com.jobsync.jobysncapi.organization.api.dto.response.ApplicationPhaseResponse;
//import com.jobsync.jobysncapi.organization.domain.model.entity.ApplicationPhase;
//import com.jobsync.jobysncapi.organization.domain.persistence.ApplicationPhaseRepository;
//import com.jobsync.jobysncapi.organization.service.ApplicationPhaseService;
//import org.modelmapper.ModelMapper;
//import org.modelmapper.TypeToken;
//import org.springframework.stereotype.Service;
//
//import java.lang.reflect.Type;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class ApplicationPhaseServiceImpl implements ApplicationPhaseService {
//
//
//    private final ApplicationPhaseRepository applicationPhaseRepository;
//
//
//    private final ModelMapper modelMapper;
//
//
//    public ApplicationPhaseServiceImpl(ApplicationPhaseRepository applicationPhaseRepository, ModelMapper modelMapper) {
//        this.applicationPhaseRepository = applicationPhaseRepository;
//
//        this.modelMapper = modelMapper;
//    }
//
//    @Override
//    public  Iterable<ApplicationPhaseResponse> getApplicationPhases(){
//        Iterable<ApplicationPhase> applicationPhases = applicationPhaseRepository.findAll();
//        Type listType = new TypeToken<List<ApplicationPhaseResponse>>() {}.getType();
//        return modelMapper.map(applicationPhases, listType);
//    }
//    @Override
//    public  Optional<ApplicationPhaseResponse> getApplicationPhaseById(Long applicationPhaseId){
//        Optional<ApplicationPhase> optionalApplicationPhase = applicationPhaseRepository.findById(applicationPhaseId);
//        if (optionalApplicationPhase.isEmpty()) {
//            return Optional.empty();
//        }
//        ApplicationPhase applicationPhase = optionalApplicationPhase.get();
//        return Optional.of(modelMapper.map(applicationPhase, ApplicationPhaseResponse.class));
//    }
//
//    @Override
//    public  ApplicationPhase createApplicationPhase(ApplicationPhaseRequest applicationPhaseRequest){
//
//        ApplicationPhase applicationPhase = modelMapper.map(applicationPhaseRequest, ApplicationPhase.class);
//        return applicationPhaseRepository.save(applicationPhase);
//    }
//
//    @Override
//    public  ApplicationPhase updateApplicationPhase(Long applicationPhaseId, ApplicationPhaseRequest applicationPhaseRequest){
//        Optional<ApplicationPhase> optionalApplicationPhase = applicationPhaseRepository.findById(applicationPhaseId);
//        if (optionalApplicationPhase.isEmpty()) {
//            throw new IllegalArgumentException("Application Phase with id " + applicationPhaseId + " not found");
//        }
//        ApplicationPhase applicationPhaseToUpdate = optionalApplicationPhase.get();
//        applicationPhaseToUpdate.setApplication(applicationPhaseRequest.getApplication());
//        applicationPhaseToUpdate.setRecruitmentPhase(applicationPhaseRequest.getRecruitmentPhase());
//        applicationPhaseToUpdate.setApplicationPhaseTasks(applicationPhaseRequest.getApplicationPhaseTasks());
//        return applicationPhaseRepository.save(applicationPhaseToUpdate);
//
//    }
//
//    @Override
//    public  void deleteApplicationPhase(Long applicationPhaseId){
//        applicationPhaseRepository.deleteById(applicationPhaseId);
//    }
//
//
//
//}
