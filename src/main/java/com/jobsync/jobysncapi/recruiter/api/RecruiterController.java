package com.jobsync.jobysncapi.recruiter.api;


import com.jobsync.jobysncapi.recruiter.domain.model.entity.Recruiter;
import com.jobsync.jobysncapi.recruiter.service.RecruiterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Recruiter", description = "Recruiter information")
@RestController
@RequestMapping("/api/v1/recruiters")
@RequiredArgsConstructor
public class RecruiterController {

    private final RecruiterService recruiterService;

    @Operation(summary = "Get all recruiters", responses = {
            @ApiResponse(description = "Recruiter company status",
                    responseCode = "201",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Recruiter.class)))
    })
    @GetMapping()
    public Iterable<Recruiter> getAll() {
        return recruiterService.getAllRecruiters();
    }


    @Operation(summary = "Verify if recruiter has a company", responses = {
            @ApiResponse(description = "Recruiter company status",
                    responseCode = "201",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Recruiter.class)))
    })
    @GetMapping("/hascompany/{recruiterId}")
    public Boolean hasCompany(@PathVariable Long recruiterId) {
        return recruiterService.hasCompany(recruiterId);
    }


    @Operation(summary = "Get recruiter by email", responses = {
            @ApiResponse(description = "Recruiter found",
                    responseCode = "201",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Recruiter.class)))
    })
    @GetMapping("/email/{email}")
    public Recruiter findByEmail(@PathVariable String email) {
        return recruiterService.findByEmail(email);
    }

    @Operation(summary = "Add recruiter to company", responses = {
            @ApiResponse(description = "Recruiter added to company",
                    responseCode = "201",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Recruiter.class)))
    })
    @PutMapping("company/add/{recruiterId}/{companyId}")
    public void addRecruiterToCompany(@PathVariable Long recruiterId, @PathVariable Long companyId) {
        recruiterService.addRecruiterToCompany(recruiterId, companyId);
    }
}
