package com.jobsync.jobysncapi.task.api.rest;

import com.jobsync.jobysncapi.task.api.dto.request.InterviewRequest;
import com.jobsync.jobysncapi.task.domain.model.entity.Interview;
import com.jobsync.jobysncapi.task.service.InterviewService;
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
    private InterviewService interviewService;

    @Operation(summary = "Get all interviews")
    @Transactional(readOnly = true)
    @GetMapping("/")
    public Iterable<Interview> getAllInterviews() {
        return interviewService.getAllInterviews();
    }

    @Operation(summary = "Get interviews by id")
    @Transactional(readOnly = true)
    @GetMapping("/{id}")
    public Interview getInterviewById(@PathVariable Long id) {
        return interviewService.getInterviewById(id);
    }

    @Operation(summary = "Create a new evaluation")
    @Transactional
    @PostMapping("/")
    public Interview createInterview(@RequestBody InterviewRequest interviewRequest) {
        return interviewService.createInterview(interviewRequest);
    }

    @Operation(summary = "Update a evaluation")
    @Transactional
    @PutMapping("/{id}")
    public Interview updateInterview(@PathVariable Long id, @RequestBody InterviewRequest interviewRequest) {
        return interviewService.updateInterviews(id, interviewRequest);
    }

    @Operation(summary = "Delete a evaluation")
    @Transactional
    @DeleteMapping("/{id}")
    public void deleteInterview(@PathVariable Long id) {
        interviewService.deleteInterview(id);
    }
}
