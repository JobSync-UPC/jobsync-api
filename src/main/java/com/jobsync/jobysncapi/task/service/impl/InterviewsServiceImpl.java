package com.jobsync.jobysncapi.task.service.impl;

import com.google.common.reflect.TypeToken;
import com.jobsync.jobysncapi.shared.exception.GlobalExceptionHandler;
import com.jobsync.jobysncapi.task.api.dto.request.InterviewRequest;
import com.jobsync.jobysncapi.task.api.dto.response.InterviewResponse;
import com.jobsync.jobysncapi.task.domain.model.entity.Evaluations;
import com.jobsync.jobysncapi.task.domain.model.entity.Interviews;
import com.jobsync.jobysncapi.task.domain.persistance.InterviewsRepository;
import com.jobsync.jobysncapi.task.service.InterviewsService;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.description.method.MethodDescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Service
public class InterviewsServiceImpl implements InterviewsService {

    private final ModelMapper modelMapper;
    private final InterviewsRepository interviewsRepository;


    @Autowired
    public InterviewsServiceImpl(InterviewsRepository interviewsRepository, ModelMapper modelMapper){
        this.interviewsRepository = interviewsRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public Interviews createInterview (InterviewRequest interviewRequest){
        Interviews interviews = modelMapper.map(interviewRequest, Interviews.class);
        return interviewsRepository.save(interviews);
    }

    @Override
    public Interviews updateInterviews(Long id, InterviewRequest interviewRequest){
        Optional<Interviews> optionalInterviews = interviewsRepository.findById(id);
        if(optionalInterviews.isEmpty()){
            throw new GlobalExceptionHandler("Interview", "interview with " + " not found");
        }
        Interviews existingInterview = optionalInterviews.get();

        existingInterview.setTitle(interviewRequest.getTitle());
        existingInterview.setStart_date(interviewRequest.getStart_date());
        existingInterview.setEnd_date(interviewRequest.getEnd_date());
        existingInterview.setLinkurl(interviewRequest.getLinkurl());

        return interviewsRepository.save(existingInterview);
    }

    @Override
    public Iterable<Interviews> getAllInterviews(){
        Iterable<Interviews> interviews = interviewsRepository.findAll();

        Type listType = new TypeToken<List<Interviews>>(){}.getType();
        return modelMapper.map(interviews, listType);
    }
    @Override
    public void deleteInterview(Long interviewId){
        interviewsRepository.deleteById(interviewId);

    }
    @Override
    public Interviews getInterviewById(Long id){ return interviewsRepository.findById(id).orElse(null);}




}
