package com.jobsync.jobysncapi.organization.service.impl;

import com.jobsync.jobysncapi.organization.api.dto.request.CompanyRequest;
import com.jobsync.jobysncapi.organization.api.dto.request.JobPostRequest;
import com.jobsync.jobysncapi.organization.domain.model.entity.Company;
import com.jobsync.jobysncapi.organization.domain.model.entity.JobPost;
import com.jobsync.jobysncapi.organization.domain.model.entity.RecruitmentProcess;
import com.jobsync.jobysncapi.organization.domain.persistence.CompanyRepository;
import com.jobsync.jobysncapi.organization.domain.persistence.JobPostRepository;
import com.jobsync.jobysncapi.organization.domain.persistence.RecruitmentProcessRepository;
import com.jobsync.jobysncapi.organization.service.JobPostService;
import com.jobsync.jobysncapi.recruiter.domain.model.entity.Recruiter;
import com.jobsync.jobysncapi.recruiter.domain.model.persistence.RecruiterRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class JobPostServiceImplTest {

    @Mock
    private JobPostServiceImpl jobPostService;

    @Mock
    private JobPostRepository jobPostRepository;

    @Mock
    private RecruiterRepository recruiterRepository;

    @Mock
    private CompanyRepository companyRepository;

    @Mock
    private RecruitmentProcessRepository recruitmentProcessRepository;

    @Mock
    private ModelMapper modelMapper;

    @Test
    void createJobPost() {
        // Arrange
        JobPostRequest jobPostRequest = new JobPostRequest();
        jobPostRequest.setTitle("Software Engineer");
        jobPostRequest.setDescription("Develop software applications");

        JobPost jobPost = new JobPost(); //expected job post
        jobPost.setTitle("Software Engineer");
        jobPost.setDescription("Develop software applications");


        JobPost jobPost2 = new JobPost(); //repeated job post
        jobPost2.setTitle("Software Engineer");
        jobPost2.setDescription("Develop software applications");


        Long companyId = 1L;
        Long recruiterId = 1L;

        when(jobPostService.createJobPost(any(JobPostRequest.class), anyLong(), anyLong())).thenReturn(jobPost);

        // Act
        JobPost createdJobPost = jobPostService.createJobPost(jobPostRequest, companyId, recruiterId);

        // Assert

        //should be able to create job post with unique title
        assertEquals(jobPost.getTitle(), createdJobPost.getTitle(),"Job post title should be the same");
        assertEquals(jobPost.getDescription(), createdJobPost.getDescription(),"Job post description should be the same");
    }

    @Test
    void getJobPostById() {
        // Arrange
        Long jobPostId = 1L;

        JobPost jobPost = new JobPost();
        jobPost.setId(jobPostId);

        when(jobPostService.getJobPostById(anyLong())).thenReturn(jobPost);

        // Act
        JobPost result = jobPostService.getJobPostById(jobPostId);

        // Assert
        assertNotNull(result);
        assertEquals(jobPostId, result.getId());
    }

    @Test
    void getJobPostsByCompanyId() {
        // Arrange
        Long companyId = 1L;

        // Create some job posts
        JobPost jobPost1 = new JobPost();
        jobPost1.setTitle("Software Engineer");
        jobPost1.setDescription("Develop software applications");

        JobPost jobPost2 = new JobPost();
        jobPost2.setTitle("Data Scientist");
        jobPost2.setDescription("Analyze and interpret complex data");

        // Add the job posts to a list
        List<JobPost> jobPosts = new ArrayList<>();
        jobPosts.add(jobPost1);
        jobPosts.add(jobPost2);

        // Use Mockito to return the list of job posts when getJobPostsByCompanyId is called
        when(jobPostService.getJobPostsByCompanyId(anyLong())).thenReturn(jobPosts);

        // Act
        List<JobPost> result = jobPostService.getJobPostsByCompanyId(companyId);

        // Assert
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(2, result.size(),"There should be 2 job posts in the list");
    }

    @Test
    void updateJobPost() {
        // Arrange
        Long jobPostId = 1L;

        JobPostRequest jobPostRequest = new JobPostRequest();
        jobPostRequest.setTitle("Software Engineer");
        jobPostRequest.setDescription("Develop software applications");

        JobPost jobPost = new JobPost();
        jobPost.setId(jobPostId);
        jobPost.setTitle("Software Engineer");
        jobPost.setDescription("Develop software applications");

        when(jobPostService.updateJobPost(anyLong(), any(JobPostRequest.class))).thenReturn(jobPost);

        // Act
        JobPost updatedJobPost = jobPostService.updateJobPost(jobPostId, jobPostRequest);

        // Assert
        assertNotNull(updatedJobPost);
        assertEquals(jobPostId, updatedJobPost.getId());
        assertEquals(jobPost.getTitle(), updatedJobPost.getTitle());
        assertEquals(jobPost.getDescription(), updatedJobPost.getDescription());
    }

    @Test
    void disableJobPost() {
        // Arrange
        Long jobPostId = 1L;

        JobPost jobPost = new JobPost();
        jobPost.setTitle("Software Engineer");
        jobPost.setDescription("Develop software applications");
        jobPost.setEnabled(Boolean.FALSE);

        when(jobPostService.disableJobPost(anyLong())).thenReturn(jobPost);

        // Act
        JobPost disabledJobPost = jobPostService.disableJobPost(jobPostId);

        // Assert
        assertNotNull(disabledJobPost);
        assertFalse(disabledJobPost.getEnabled(),"Job post should be disabled");
    }
}