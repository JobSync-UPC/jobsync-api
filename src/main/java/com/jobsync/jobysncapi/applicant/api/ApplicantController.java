package com.jobsync.jobysncapi.applicant.api;

import com.jobsync.jobysncapi.applicant.model.entity.Applicant;
import com.jobsync.jobysncapi.applicant.service.ApplicantService;
import com.jobsync.jobysncapi.applicant.service.dto.UpdateApplicantProfileDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.Update;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "Applicant", description = "Edit and view applicant information")
@RestController
@RequestMapping("/api/v1/applicants")
@RequiredArgsConstructor
public class ApplicantController {

    private final ApplicantService applicantService;

    @Operation(summary = "Get all applicants", responses = {
            @ApiResponse(description = "All applicants found",
                    responseCode = "201",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Applicant.class)))
    })
    @GetMapping
    public Iterable<Applicant> getAllApplicants() {
        return applicantService.getAllApplicants();
    }

    @Operation(summary = "Get applicant by id", responses = {
            @ApiResponse(description = "Applicant found",
                    responseCode = "201",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Applicant.class)))
    })
    @GetMapping("/{id}")
    public Applicant getApplicantById(@PathVariable Long id) {
        return applicantService.getApplicantById(id).orElse(null);
    }

    @Operation(summary = "Update applicant professional profile", responses = {
            @ApiResponse(description = "Applicant found",
                    responseCode = "201",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UpdateApplicantProfileDto.class)))
    })
    @PutMapping("/{id}")
    public Applicant updateApplicant(@PathVariable Long id, @RequestBody UpdateApplicantProfileDto updatedApplicant) {
        return applicantService.updateApplicant(id, updatedApplicant);
    }

    @Operation(summary = "Update applicant CV file URL", responses = {
            @ApiResponse(description = "Applicant found",
                    responseCode = "201",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UpdateApplicantProfileDto.class)))
    })
    @PostMapping("/{id}/cv")
    public Applicant updateApplicantCV(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        return applicantService.updateApplicantCV(id, file);
    }
}