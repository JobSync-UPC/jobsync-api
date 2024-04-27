package com.jobsync.jobysncapi.task.api.rest;

import com.jobsync.jobysncapi.task.api.dto.request.EvaluationsRequest;
import com.jobsync.jobysncapi.task.domain.model.entity.Evaluations;
import com.jobsync.jobysncapi.task.service.EvaluationsService;
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
    private EvaluationsService evaluationsService;

    @Operation(summary = "Get all evaluations")
    @Transactional(readOnly = true)
    @GetMapping("/")
    public Iterable<Evaluations> getAllEvaluations() {
        return evaluationsService.getAllEvaluations();
    }

    @Operation(summary = "Get evaluation by id")
    @Transactional(readOnly = true)
    @GetMapping("/{id}")
    public Evaluations getEvaluationById(@PathVariable Long id) {
        return evaluationsService.getEvaluationById(id);
    }

    @Operation(summary = "Create a new evaluation")
    @Transactional
    @PostMapping("/")
    public Evaluations createEvaluation(@RequestBody EvaluationsRequest evaluationsRequest) {
        return evaluationsService.createEvaluations(evaluationsRequest);
    }

    @Operation(summary = "Update a evaluation")
    @Transactional
    @PutMapping("/{id}")
    public Evaluations updateEvaluation(@PathVariable Long id, @RequestBody EvaluationsRequest evaluationsRequest) {
        return evaluationsService.updateEvaluations(id, evaluationsRequest);
    }

    @Operation(summary = "Delete a evaluation")
    @Transactional
    @DeleteMapping("/{id}")
    public void deleteEvaluation(@PathVariable Long id) {
        evaluationsService.deleteEvaluation(id);
    }
}
