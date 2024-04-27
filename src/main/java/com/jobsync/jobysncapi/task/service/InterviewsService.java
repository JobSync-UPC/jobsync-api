package com.jobsync.jobysncapi.task.service;

import com.jobsync.jobysncapi.task.api.dto.request.InterviewRequest;
import com.jobsync.jobysncapi.task.api.dto.response.InterviewResponse;

import com.jobsync.jobysncapi.task.domain.model.entity.Interviews;

public interface InterviewsService {
    public abstract Interviews createInterview (InterviewRequest interviewRequest);
    public abstract Interviews updateInterviews(Long id, InterviewRequest interviewRequest);
    public Iterable<InterviewResponse> getAllInterviews();
    public abstract void deleteInterview(Long interviewId);
    public abstract Interviews getInterviewById(Long id);
}
