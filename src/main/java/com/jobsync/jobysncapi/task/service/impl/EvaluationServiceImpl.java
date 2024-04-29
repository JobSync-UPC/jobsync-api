package com.jobsync.jobysncapi.task.service.impl;

import com.google.common.reflect.TypeToken;
import com.jobsync.jobysncapi.shared.exception.GlobalExceptionHandler;
import com.jobsync.jobysncapi.task.api.dto.request.EvaluationsRequest;
import com.jobsync.jobysncapi.task.domain.model.entity.Evaluation;
import com.jobsync.jobysncapi.task.domain.persistance.EvaluationRepository;
import com.jobsync.jobysncapi.task.service.EvaluationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.lang.reflect.Type;
import java.util.List;
@Service
public class EvaluationServiceImpl implements EvaluationService {

    private final EvaluationRepository evaluationsRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public EvaluationServiceImpl(EvaluationRepository evaluationsRepository, ModelMapper modelMapper){
        this.evaluationsRepository = evaluationsRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Evaluation createEvaluations(EvaluationsRequest evaluationsRequest){
        Evaluation evaluation = modelMapper.map(evaluationsRequest, Evaluation.class);
        return evaluationsRepository.save(evaluation);
    }

    @Override
    public Evaluation updateEvaluations(Long id, EvaluationsRequest evaluationsRequest) {
        Optional<Evaluation> optionalEvaluations = evaluationsRepository.findById(id);
        if (optionalEvaluations.isEmpty()) {
            throw new GlobalExceptionHandler("Evaluation", "Evaluation with id " + id + " not found");
        }

        Evaluation existingEvaluation = optionalEvaluations.get();

        existingEvaluation.setTitle(evaluationsRequest.getTitle());
        existingEvaluation.setDescription(evaluationsRequest.getDescription());

        return evaluationsRepository.save(existingEvaluation);
    }

    @Override
    public void deleteEvaluation(Long evaluationId){
        evaluationsRepository.deleteById(evaluationId);

    }

    @Override
    public Iterable<Evaluation> getAllEvaluations(){
        Iterable<Evaluation> evaluations = evaluationsRepository.findAll();

        Type listType =  new TypeToken<List<Evaluation>>() {}.getType();
        return modelMapper.map(evaluations, listType);
    }

    @Override
    public Evaluation getEvaluationById(Long id){ return evaluationsRepository.findById(id).orElse(null);}



}
