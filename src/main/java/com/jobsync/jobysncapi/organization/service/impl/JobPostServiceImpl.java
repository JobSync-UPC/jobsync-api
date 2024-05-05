package com.jobsync.jobysncapi.organization.service.impl;

import com.jobsync.jobysncapi.organization.api.dto.request.JobPostRequest;
import com.jobsync.jobysncapi.organization.domain.model.entity.Company;
import com.jobsync.jobysncapi.organization.domain.model.entity.JobPost;
import com.jobsync.jobysncapi.organization.domain.model.entity.RecruitmentProcess;
import com.jobsync.jobysncapi.organization.domain.persistence.JobPostRepository;
import com.jobsync.jobysncapi.organization.domain.persistence.RecruitmentProcessRepository;
import com.jobsync.jobysncapi.organization.service.JobPostService;
import com.jobsync.jobysncapi.organization.service.RecruitmentProcessService;
import com.jobsync.jobysncapi.recruiter.domain.model.entity.Recruiter;
import com.jobsync.jobysncapi.recruiter.domain.model.persistence.RecruiterRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
public class JobPostServiceImpl implements JobPostService {


    private final ModelMapper modelMapper;
    private final JobPostRepository jobPostRepository;

    private final RecruitmentProcessRepository recruitmentProcessRepository;

    private final RecruiterRepository recruiterRepository;

    private final RecruitmentProcessService recruitmentProcessService;


    @Autowired
    public JobPostServiceImpl(ModelMapper modelMapper, JobPostRepository jobPostRepository,
                              RecruitmentProcessRepository recruitmentProcessRepository,
                              RecruiterRepository recruiterRepository, RecruitmentProcessService recruitmentProcessService){
        this.modelMapper = modelMapper;
        this.jobPostRepository = jobPostRepository;
        this.recruitmentProcessRepository = recruitmentProcessRepository;
        this.recruiterRepository = recruiterRepository;
        this.recruitmentProcessService = recruitmentProcessService;
    }


    @Override
    public JobPost createJobPost(JobPostRequest jobPostRequest, Long companyId, Long recruiterId) {
        Optional<Recruiter> optionalRecruiter = recruiterRepository.findById(recruiterId);
        if (optionalRecruiter.isEmpty()) {
            throw new EntityNotFoundException("Recruiter with id " + recruiterId + " not found");
        }
        Recruiter recruiter = optionalRecruiter.get();

        // Check if job post with title already exists within the same company
        if (jobPostRepository.findByTitleAndRecruitmentProcess_CompanyId(jobPostRequest.getTitle(), companyId).isPresent()) {
            throw new IllegalArgumentException("Job post with title " + jobPostRequest.getTitle() + " already exists");
        }

        // Job pos title and description are required and should not be empty
        if (jobPostRequest.getTitle().isEmpty() || jobPostRequest.getDescription().isEmpty()) {
            throw new IllegalArgumentException("Job post title and description are required");
        }

        RecruitmentProcess recruitmentProcess = recruitmentProcessService.createRecruitmentProcess(companyId);

        JobPost jobPost = JobPost.builder()
                .title(jobPostRequest.getTitle())
                .description(jobPostRequest.getDescription())
                .enabled(true)
                .recruitmentProcess(recruitmentProcess)
                .build();

        jobPost = jobPostRepository.save(jobPost);

        recruitmentProcess.setJobPost(jobPost);
        recruitmentProcessRepository.save(recruitmentProcess);

        return jobPost;
    }



    @Override
    public JobPost updateJobPost(Long id, JobPostRequest jobPostRequest) {
        JobPost existingJobPost = jobPostRepository.findById(id).orElse(null);

        if (existingJobPost != null) {
            modelMapper.map(jobPostRequest, existingJobPost);
            return jobPostRepository.save(existingJobPost);
        } else {
            throw new IllegalArgumentException("No existe el post a modificar");
        }
    }

    @Override
    public void deleteJobPost(Long jobPostId) {
        jobPostRepository.deleteById(jobPostId);
    }

    @Override
    public JobPost getJobPostById(Long jobPostId) {
        return jobPostRepository.findById(jobPostId).orElse(null);
    }

    @Override
    public Iterable<JobPost> getAllJobPosts() {
        return jobPostRepository.findAll();
    }

    @Override
    public List<JobPost> getJobPostsByCompanyId(Long companyId) {

        RecruitmentProcess recruitmentProcess = recruitmentProcessRepository.findByCompanyId(companyId);
        if (recruitmentProcess == null) {
            return Collections.emptyList();
        }
        return jobPostRepository.findByRecruitmentProcess(recruitmentProcess);
    }

    @Override
    public List<JobPost> getJobPostsByRecruiterId(Long recruiterId) {
        Optional<Recruiter> optionalRecruiter = recruiterRepository.findById(recruiterId);
        if (optionalRecruiter.isEmpty()) {
            return Collections.emptyList();
        }
        Recruiter recruiter = optionalRecruiter.get();

        List<RecruitmentProcess> recruitmentProcesses = recruitmentProcessRepository.findByCompany(recruiter.getCompany());
        List<JobPost> jobPosts = new ArrayList<>();

        for (RecruitmentProcess recruitmentProcess : recruitmentProcesses) {
            jobPosts.addAll(jobPostRepository.findByRecruitmentProcess(recruitmentProcess));
        }
        return jobPosts;
    }


    @Override
    public JobPost disableJobPost(Long jobPostId) {
        return jobPostRepository.findById(jobPostId)
                .map(jobPost -> {
                    jobPost.setEnabled(Boolean.FALSE);
                    return jobPostRepository.save(jobPost);
                })
                .orElseThrow(() -> new RuntimeException("JobPost not found"));
    }

    @Override
    public JobPost enableJobPost(Long jobPostId) {
        return jobPostRepository.findById(jobPostId)
                .map(jobPost -> {
                    jobPost.setEnabled(Boolean.TRUE);
                    return jobPostRepository.save(jobPost);
                })
                .orElseThrow(() -> new RuntimeException("JobPost not found"));
    }

}
