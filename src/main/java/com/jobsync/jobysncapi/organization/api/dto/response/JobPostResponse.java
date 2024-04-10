package com.jobsync.jobysncapi.organization.api.dto.response;

import com.jobsync.jobysncapi.organization.domain.model.entity.RecruitmentProcess;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;


@Data
public class JobPostResponse {

    private Long id;
    private String title;
    private String description;
    private Boolean enabled = true;

    @OneToOne
    @JoinColumn(name = "recruitment_processes_id", referencedColumnName = "id")
    private RecruitmentProcess recruitmentProcess;
}
