package com.jobsync.jobysncapi.organization.domain.persistence;

import com.jobsync.jobysncapi.organization.domain.model.entity.JobPost;
import com.jobsync.jobysncapi.organization.domain.model.entity.RecruitmentProcess;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JobPostRepository extends JpaRepository<JobPost, Long> {


    Optional<JobPost> findByTitle(String title);

    List<JobPost> findByRecruitmentProcess(RecruitmentProcess recruitmentProcess);
    Boolean existsJobPostByTitle(String title);

    Optional<Object> findByTitleAndRecruitmentProcess_CompanyId(String title, Long companyId);
}
