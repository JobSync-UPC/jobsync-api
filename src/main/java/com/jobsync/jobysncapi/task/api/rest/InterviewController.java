package com.jobsync.jobysncapi.task.api.rest;

import com.jobsync.jobysncapi.task.api.dto.request.InterviewRequest;
import com.jobsync.jobysncapi.task.domain.model.entity.Interviews;
import com.jobsync.jobysncapi.task.service.InterviewsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Interview Controller", description = "Create, read, update, and delete interviews")
@RestController
@RequestMapping("/api/v1/interviews")
public class InterviewController {

    @Autowired
    private InterviewsService interviewsService;

    @Operation(summary = "Get all interviews")
    @Transactional(readOnly = true)
    @GetMapping("/")
    public Iterable<Interviews> getAllInterviews() {
        return interviewsService.getAllInterviews();
    }

    @Operation(summary = "Get interviews by id")
    @Transactional(readOnly = true)
    @GetMapping("/{id}")
    public Interviews getInterviewById(@PathVariable Long id) {
        return interviewsService.getInterviewById(id);
    }

    @Operation(summary = "Create a new evaluation")
    @Transactional
    @PostMapping("/")
    public Interviews createInterview(@RequestBody InterviewRequest interviewRequest) {
        return interviewsService.createInterview(interviewRequest);
    }

    @Operation(summary = "Update a evaluation")
    @Transactional
    @PutMapping("/{id}")
    public Interviews updateInterview(@PathVariable Long id, @RequestBody InterviewRequest interviewRequest) {
        return interviewsService.updateInterviews(id, interviewRequest);
    }

    @Operation(summary = "Delete a evaluation")
    @Transactional
    @DeleteMapping("/{id}")
    public void deleteInterview(@PathVariable Long id) {
        interviewsService.deleteInterview(id);
    }
}
