package com.jobsync.jobysncapi.organization.api.dto.request;

import com.jobsync.jobysncapi.applicant.model.entity.Applicant;
import com.jobsync.jobysncapi.organization.domain.model.entity.RecruitmentProcess;
import lombok.Data;

@Data
public class ApplicationRequest {
    private Long applicantId;
    private Long recruitmentProcessId;
}
