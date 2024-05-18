package com.jobsync.jobysncapi.organization.service.impl;

import com.jobsync.jobysncapi.organization.api.dto.request.ApplicationPhaseTaskRequest;
import com.jobsync.jobysncapi.organization.api.dto.response.ApplicationPhaseTaskResponse;
import com.jobsync.jobysncapi.organization.domain.model.entity.ApplicationPhaseTask;
import com.jobsync.jobysncapi.organization.domain.persistence.ApplicationPhaseTaskRepository;
import com.jobsync.jobysncapi.organization.service.ApplicationPhaseTaskService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Service
public class ApplicationPhaseTaskServiceImpl implements ApplicationPhaseTaskService {

    private final ApplicationPhaseTaskRepository applicationPhaseTaskRepository;

    private final ModelMapper modelMapper;

    public ApplicationPhaseTaskServiceImpl(ApplicationPhaseTaskRepository applicationPhaseTaskRepository, ModelMapper modelMapper) {
        this.applicationPhaseTaskRepository = applicationPhaseTaskRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Iterable<ApplicationPhaseTaskResponse> getApplicationPhaseTasks(){
        Iterable<ApplicationPhaseTask> applicationPhaseTasks = applicationPhaseTaskRepository.findAll();
        Type listType = new TypeToken<List<ApplicationPhaseTaskResponse>>() {}.getType();
        return modelMapper.map(applicationPhaseTasks, listType);
    }

    @Override
    public Optional<ApplicationPhaseTaskResponse> getApplicationPhaseTaskById(Long applicationPhaseTaskId){

        Optional<ApplicationPhaseTask> optionalApplicationPhaseTask = applicationPhaseTaskRepository.findById(applicationPhaseTaskId);
        if (optionalApplicationPhaseTask.isEmpty()) {
            return Optional.empty();
        }
        ApplicationPhaseTask applicationPhaseTask = optionalApplicationPhaseTask.get();
        return Optional.of(modelMapper.map(applicationPhaseTask, ApplicationPhaseTaskResponse.class));
    }

    @Override
    public ApplicationPhaseTask createApplicationPhaseTask(ApplicationPhaseTaskRequest applicationPhaseTaskRequest){
        ApplicationPhaseTask applicationPhaseTask = modelMapper.map(applicationPhaseTaskRequest, ApplicationPhaseTask.class);
        return applicationPhaseTaskRepository.save(applicationPhaseTask);

    }

    @Override
    public ApplicationPhaseTask updateApplicationPhaseTask(Long applicationPhaseTaskId, ApplicationPhaseTaskRequest applicationPhaseTaskRequest){
        Optional<ApplicationPhaseTask> optionalApplicationPhaseTask = applicationPhaseTaskRepository.findById(applicationPhaseTaskId);
        if (optionalApplicationPhaseTask.isEmpty()) {
            throw new IllegalArgumentException("Application Phase Task with id " + applicationPhaseTaskId + " not found");
        }
        ApplicationPhaseTask applicationPhaseTask = optionalApplicationPhaseTask.get();

        modelMapper.map(applicationPhaseTaskRequest, applicationPhaseTask);
        return applicationPhaseTaskRepository.save(applicationPhaseTask);
    }


    @Override
    public void deleteApplicationPhaseTask(Long applicationPhaseTaskId){
        applicationPhaseTaskRepository.deleteById(applicationPhaseTaskId);
    }


}
