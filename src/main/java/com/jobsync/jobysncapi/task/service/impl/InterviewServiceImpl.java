package com.jobsync.jobysncapi.task.service.impl;

import com.google.common.reflect.TypeToken;
import com.jobsync.jobysncapi.shared.exception.GlobalExceptionHandler;
import com.jobsync.jobysncapi.task.api.dto.request.InterviewRequest;
import com.jobsync.jobysncapi.task.domain.model.entity.Interview;
import com.jobsync.jobysncapi.task.domain.persistance.InterviewsRepository;
import com.jobsync.jobysncapi.task.service.InterviewService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Service
public class InterviewServiceImpl implements InterviewService {

    private final ModelMapper modelMapper;
    private final InterviewsRepository interviewsRepository;


    @Autowired
    public InterviewServiceImpl(InterviewsRepository interviewsRepository, ModelMapper modelMapper){
        this.interviewsRepository = interviewsRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public Interview createInterview (InterviewRequest interviewRequest){
        Interview interview = modelMapper.map(interviewRequest, Interview.class);
        return interviewsRepository.save(interview);
    }

    @Override
    public Interview updateInterviews(Long id, InterviewRequest interviewRequest){
        Optional<Interview> optionalInterviews = interviewsRepository.findById(id);
        if(optionalInterviews.isEmpty()){
            throw new GlobalExceptionHandler("Interview", "interview with " + " not found");
        }
        Interview existingInterview = optionalInterviews.get();

        existingInterview.setTitle(interviewRequest.getTitle());
        existingInterview.setStart_date(interviewRequest.getStart_date());
        existingInterview.setEnd_date(interviewRequest.getEnd_date());
        existingInterview.setLinkurl(interviewRequest.getLinkurl());

        return interviewsRepository.save(existingInterview);
    }

    @Override
    public Iterable<Interview> getAllInterviews(){
        Iterable<Interview> interviews = interviewsRepository.findAll();

        Type listType = new TypeToken<List<Interview>>(){}.getType();
        return modelMapper.map(interviews, listType);
    }
    @Override
    public void deleteInterview(Long interviewId){
        interviewsRepository.deleteById(interviewId);

    }
    @Override
    public Interview getInterviewById(Long id){ return interviewsRepository.findById(id).orElse(null);}




}
