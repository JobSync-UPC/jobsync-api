package com.jobsync.jobysncapi.organization.api.rest;


import com.jobsync.jobysncapi.organization.api.dto.request.RecruitmentPhaseRequest;
import com.jobsync.jobysncapi.organization.domain.model.entity.JobPost;
import com.jobsync.jobysncapi.organization.domain.model.entity.RecruitmentPhase;
import com.jobsync.jobysncapi.organization.domain.model.entity.RecruitmentProcess;
import com.jobsync.jobysncapi.organization.service.JobPostService;
import com.jobsync.jobysncapi.organization.service.RecruitmentPhaseService;
import com.jobsync.jobysncapi.organization.service.RecruitmentProcessService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Recruitment Process Controller", description = "Create, read, update, and delete recruitment processes")
@RestController
@RequestMapping("/api/v1/recruitment-processes")
public class RecruitmentProcessController {

    @Autowired
    private RecruitmentProcessService recruitmentProcessService;

    @Operation(summary = "Get all recruitment processes")
    @GetMapping("/")
    public Iterable<RecruitmentProcess> getAllRecruitmentProcesses() {
        return recruitmentProcessService.getAllRecruitmentProcesses();
    }

    @Operation(summary = "Get recruitment process by id")
    @GetMapping("/{id}")
    public RecruitmentProcess getRecruitmentProcessById(@PathVariable Long id) {
        return recruitmentProcessService.getRecruitmentProcessById(id);
    }

    @Operation(summary = "Finish/Reopen recruitment process")
    @PutMapping("/update-enabled/{recruitmentProcessId}")
    public RecruitmentProcess finishRecruitmentProcess(@PathVariable Long recruitmentProcessId) {
        return recruitmentProcessService.updateEnabledRecruitmentProcess(recruitmentProcessId);
    }

    @Operation(summary = "Delete recruitment process")
    @GetMapping("/delete/{recruitmentProcessId}")
    public void deleteRecruitmentProcess(@PathVariable Long recruitmentProcessId) {
        recruitmentProcessService.deleteRecruitmentProcess(recruitmentProcessId);
    }

    @Operation(summary = "Get Recruitment process by company id")
    @GetMapping("/company/{companyId}")
    public Iterable<RecruitmentProcess> getRecruitmentProcessByCompanyId(@PathVariable Long companyId) {
        return recruitmentProcessService.getRecruitmentProcessesByCompanyId(companyId);
    }

    @Operation(summary = "Check if recruitment process is from company")
    @GetMapping("is-from-company")
    public boolean isRecruitmentProcessFromCompany(@RequestParam Long recruitmentProcessId, @RequestParam Long companyId) {
        return recruitmentProcessService.isRecruitmentProcessFromCompany(recruitmentProcessId, companyId);
    }
}
