package com.jobsync.jobysncapi.task.service.impl;

import com.google.common.reflect.TypeToken;
import com.jobsync.jobysncapi.shared.exception.GlobalExceptionHandler;
import com.jobsync.jobysncapi.task.api.dto.request.EvaluationsRequest;
import com.jobsync.jobysncapi.task.api.dto.response.EvaluationsResponse;
import com.jobsync.jobysncapi.task.domain.model.entity.Evaluations;
import com.jobsync.jobysncapi.task.domain.persistance.EvaluationsRepository;
import com.jobsync.jobysncapi.task.service.EvaluationsService;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.description.method.MethodDescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.lang.reflect.Type;
import java.util.List;
@Service
public class EvaluationServiceImpl implements EvaluationsService {

    private final EvaluationsRepository evaluationsRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public EvaluationServiceImpl(EvaluationsRepository evaluationsRepository, ModelMapper modelMapper){
        this.evaluationsRepository = evaluationsRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Evaluations createEvaluations(EvaluationsRequest evaluationsRequest){
        Evaluations evaluations = modelMapper.map(evaluationsRequest, Evaluations.class);
        return evaluationsRepository.save(evaluations);
    }

    @Override
    public Evaluations updateEvaluations(Long id, EvaluationsRequest evaluationsRequest) {
        Optional<Evaluations> optionalEvaluations = evaluationsRepository.findById(id);
        if (optionalEvaluations.isEmpty()) {
            throw new GlobalExceptionHandler("Evaluation", "Evaluation with id " + id + " not found");
        }

        Evaluations existingEvaluations = optionalEvaluations.get();

        existingEvaluations.setTitle(evaluationsRequest.getTitle());
        existingEvaluations.setDescription(evaluationsRequest.getDescription());

        return evaluationsRepository.save(existingEvaluations);
    }

    @Override
    public void deleteEvaluation(Long evaluationId){
        evaluationsRepository.deleteById(evaluationId);

    }

    @Override
    public Iterable<Evaluations> getAllEvaluations(){
        Iterable<Evaluations> evaluations = evaluationsRepository.findAll();

        Type listType =  new TypeToken<List<Evaluations>>() {}.getType();
        return modelMapper.map(evaluations, listType);
    }

    @Override
    public Evaluations getEvaluationById(Long id){ return evaluationsRepository.findById(id).orElse(null);}



}
