package com.jobsync.jobysncapi.task.service;

import com.jobsync.jobysncapi.task.api.dto.request.InterviewRequest;

import com.jobsync.jobysncapi.task.domain.model.entity.Interview;

public interface InterviewService {
    public abstract Interview createInterview (InterviewRequest interviewRequest);
    public abstract Interview updateInterviews(Long id, InterviewRequest interviewRequest);
    public Iterable<Interview> getAllInterviews();
    public abstract void deleteInterview(Long interviewId);
    public abstract Interview getInterviewById(Long id);
}
