package com.jobsync.jobysncapi.organization.api.rest;


import com.jobsync.jobysncapi.organization.api.dto.request.JobPostRequest;
import com.jobsync.jobysncapi.organization.domain.model.entity.Company;
import com.jobsync.jobysncapi.organization.domain.model.entity.JobPost;
import com.jobsync.jobysncapi.organization.service.JobPostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Job Posts Controller", description = "Create, read, update, and delete job posts")
@RestController
@RequestMapping("/api/v1/job-posts")
public class JobPostController {

    @Autowired
    private JobPostService jobPostService;


    @Operation(summary = "Get all job posts")
    @Transactional(readOnly = true)
    @GetMapping("/")
    public Iterable<JobPost> getAllJobPosts() {
        return jobPostService.getAllJobPosts();
    }

    @Operation(summary = "Get job post by id")
    @Transactional(readOnly = true)
    @GetMapping("/{id}")
    public JobPost getJobPostById(@PathVariable Long id) {
        return jobPostService.getJobPostById(id);
    }


    @Operation(summary = "Create a new job post")
    @Transactional
    @PostMapping("/")
    public JobPost createJobPost(@RequestBody JobPostRequest jobPostRequest,
                                 @RequestParam Long companyId,
                                 @RequestParam Long recruiterId) {

        return jobPostService.createJobPost(jobPostRequest,companyId, recruiterId);
    }


    @Operation(summary = "Update a job post")
    @Transactional
    @PutMapping("/{id}")
    public JobPost updateJobPost(@PathVariable Long id, @RequestBody JobPostRequest jobPostRequest) {
        return jobPostService.updateJobPost(id, jobPostRequest);
    }

    @Operation(summary = "Delete a job post")
    @Transactional
    @DeleteMapping("/{id}")
    public void deleteJobPost(@PathVariable Long id) {
        jobPostService.deleteJobPost(id);
    }


    @Operation(summary = "Enable job post by id", responses = {
            @ApiResponse(description = "Company found and enabled",
                    responseCode = "201",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Company.class)))
    })
    @PutMapping("/enable/{id}")
    public JobPost enableJobPost(@PathVariable Long id) {
        return jobPostService.enableJobPost(id);
    }

    @Operation(summary = "Disable job post by id", responses = {
            @ApiResponse(description = "Company found and disabled",
                    responseCode = "201",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Company.class)))
    })
    @PutMapping("/disable/{id}")
    public JobPost disableJobPost(@PathVariable Long id) {
        return jobPostService.disableJobPost(id);
    }



}
