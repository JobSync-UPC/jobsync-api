package com.jobsync.jobysncapi.task.api.rest;

import com.jobsync.jobysncapi.task.api.dto.request.EvaluationsRequest;
import com.jobsync.jobysncapi.task.domain.model.entity.Evaluation;
import com.jobsync.jobysncapi.task.service.EvaluationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Evaluation Controller", description = "Create, read, update, and delete evaluations")
@RestController
@RequestMapping("/api/v1/evaluations")
public class EvaluationController {

    @Autowired
    private EvaluationService evaluationService;

    @Operation(summary = "Get all evaluations")
    @Transactional(readOnly = true)
    @GetMapping("/")
    public Iterable<Evaluation> getAllEvaluations() {
        return evaluationService.getAllEvaluations();
    }

    @Operation(summary = "Get evaluation by id")
    @Transactional(readOnly = true)
    @GetMapping("/{id}")
    public Evaluation getEvaluationById(@PathVariable Long id) {
        return evaluationService.getEvaluationById(id);
    }

    @Operation(summary = "Create a new evaluation")
    @Transactional
    @PostMapping("/")
    public Evaluation createEvaluation(@RequestBody EvaluationsRequest evaluationsRequest) {
        return evaluationService.createEvaluations(evaluationsRequest);
    }

    @Operation(summary = "Update a evaluation")
    @Transactional
    @PutMapping("/{id}")
    public Evaluation updateEvaluation(@PathVariable Long id, @RequestBody EvaluationsRequest evaluationsRequest) {
        return evaluationService.updateEvaluations(id, evaluationsRequest);
    }

    @Operation(summary = "Delete a evaluation")
    @Transactional
    @DeleteMapping("/{id}")
    public void deleteEvaluation(@PathVariable Long id) {
        evaluationService.deleteEvaluation(id);
    }
}
