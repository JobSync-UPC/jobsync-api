package com.jobsync.jobysncapi.task.service;

import com.jobsync.jobysncapi.task.api.dto.request.EvaluationsRequest;
import com.jobsync.jobysncapi.task.domain.model.entity.Evaluation;

public interface EvaluationService {
    public abstract Evaluation createEvaluations(EvaluationsRequest evaluationsRequest);
    public abstract Evaluation updateEvaluations(Long id, EvaluationsRequest evaluationsRequest);
    public abstract void deleteEvaluation(Long evaluationId);
    public Iterable<Evaluation> getAllEvaluations();
    public abstract Evaluation getEvaluationById(Long id);
}
