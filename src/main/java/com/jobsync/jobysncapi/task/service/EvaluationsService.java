package com.jobsync.jobysncapi.task.service;

import com.jobsync.jobysncapi.task.api.dto.request.EvaluationsRequest;
import com.jobsync.jobysncapi.task.api.dto.response.EvaluationsResponse;
import com.jobsync.jobysncapi.task.domain.model.entity.Evaluations;

public interface EvaluationsService {
    public abstract Evaluations createEvaluations(EvaluationsRequest evaluationsRequest);
    public abstract Evaluations updateEvaluations(Long id, EvaluationsRequest evaluationsRequest);
    public abstract void deleteEvlauation(Long evaluationId);
    public Iterable<EvaluationsResponse> getAllEvaluations();
    public abstract Evaluations getEvaluationById(Long id);
}
