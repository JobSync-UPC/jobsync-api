package com.jobsync.jobysncapi.organization.service;

import com.jobsync.jobysncapi.organization.api.dto.request.JobPostRequest;
import com.jobsync.jobysncapi.organization.domain.model.entity.JobPost;

import java.util.List;

public interface JobPostService {

    public abstract JobPost createJobPost(JobPostRequest jobPostRequest, Long companyId, Long recruiterId);

    public abstract JobPost updateJobPost(Long id, JobPostRequest jobPostRequest);

    public abstract void deleteJobPost(Long jobPostId);

    public abstract JobPost getJobPostById(Long jobPostId);

    public Iterable<JobPost> getAllJobPosts();

    public abstract List<JobPost> getJobPostsByCompanyId(Long companyId);

    public abstract List<JobPost> getJobPostsByRecruiterId(Long recruiterId);

    public abstract JobPost enableJobPost(Long jobPostId);

    public abstract JobPost disableJobPost(Long jobPostId);

}
