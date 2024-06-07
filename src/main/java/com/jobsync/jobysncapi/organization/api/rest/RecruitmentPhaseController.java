package com.jobsync.jobysncapi.organization.api.rest;

import com.jobsync.jobysncapi.organization.api.dto.request.RecruitmentPhaseRequest;
import com.jobsync.jobysncapi.organization.domain.model.entity.RecruitmentPhase;
import com.jobsync.jobysncapi.organization.service.RecruitmentPhaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Recruitment Phases Controller", description = "Create, read, update, and delete recruitment phases")
@RestController
@RequestMapping("/api/v1/recruitment-phases")
public class RecruitmentPhaseController {

    @Autowired
    private RecruitmentPhaseService recruitmentPhaseService;

    @Operation(summary = "Create a new recruitment phase")
    @PostMapping("/create-phase")
    public RecruitmentPhase createRecruitmentPhase(@RequestBody RecruitmentPhaseRequest recruitmentPhaseRequest) {
        return recruitmentPhaseService.createRecruitmentPhase(recruitmentPhaseRequest);
    }

    @Operation(summary = "Update an existing recruitment phase")
    @PostMapping("/update-phase/{recruitmentPhaseId}")
    public RecruitmentPhase updateRecruitmentPhase(@PathVariable Long recruitmentPhaseId, @RequestBody RecruitmentPhaseRequest recruitmentPhaseRequest) {
        return recruitmentPhaseService.updateRecruitmentPhase(recruitmentPhaseId,recruitmentPhaseRequest);
    }

    @Operation(summary = "Get recruitment phase by id")
    @GetMapping("/{recruitmentPhaseId}")
    public RecruitmentPhase getRecruitmentPhaseById(@PathVariable Long recruitmentPhaseId) {
        return recruitmentPhaseService.getRecruitmentPhaseById(recruitmentPhaseId);
    }
}
