package com.jobsync.jobysncapi.organization.service.impl;

import com.jobsync.jobysncapi.organization.api.dto.request.ApplicationRequest;
import com.jobsync.jobysncapi.organization.api.dto.response.ApplicationResponse;
import com.jobsync.jobysncapi.organization.domain.model.entity.Application;
import com.jobsync.jobysncapi.organization.domain.persistence.ApplicationRepository;
import com.jobsync.jobysncapi.organization.service.ApplicationService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;

    private final ModelMapper modelMapper;

    public ApplicationServiceImpl(ApplicationRepository applicationRepository, ModelMapper modelMapper) {
        this.applicationRepository = applicationRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public Application createApplication(ApplicationRequest applicationRequest, Long id){
        Application application = modelMapper.map(applicationRequest, Application.class);
        return applicationRepository.save(application);
    }
    @Override
    public Application updateApplication(Long id, ApplicationRequest applicationRequest){
        Application application = modelMapper.map(applicationRequest, Application.class);
        application.setId(id);
        return applicationRepository.save(application);

    }
    @Override
    public void deleteApplication(Long applicationId){
        applicationRepository.deleteById(applicationId);
    }
    @Override
    public Iterable<ApplicationResponse> getAllApplications(){
        Iterable<Application> applications = applicationRepository.findAll();
        Type listType = new TypeToken<Iterable<ApplicationResponse>>() {}.getType();
        return modelMapper.map(applications, listType);
    }
    @Override
    public ApplicationResponse getApplicationById(Long applicationId){
        Application application = applicationRepository.findById(applicationId).orElse(null);
        return modelMapper.map(application, ApplicationResponse.class);
    }


}
