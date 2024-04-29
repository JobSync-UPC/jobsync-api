package com.jobsync.jobysncapi.organization.api.rest;


import com.jobsync.jobysncapi.organization.domain.model.entity.RecruitmentProcess;
import com.jobsync.jobysncapi.organization.service.RecruitmentProcessService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @Operation(summary = "Update recruitment process")
    @GetMapping("/update/{recruitmentProcessId}")
    public RecruitmentProcess updateRecruitmentProcess(@PathVariable Long recruitmentProcessId) {
        return recruitmentProcessService.updateRecruitmentProcess(recruitmentProcessId);
    }

    @Operation(summary = "Delete recruitment process")
    @GetMapping("/delete/{recruitmentProcessId}")
    public void deleteRecruitmentProcess(@PathVariable Long recruitmentProcessId) {
        recruitmentProcessService.deleteRecruitmentProcess(recruitmentProcessId);
    }




}
