package com.jobsync.jobysncapi.organization.api.dto.request;

import com.jobsync.jobysncapi.applicant.model.entity.Applicant;
import com.jobsync.jobysncapi.organization.domain.model.entity.RecruitmentProcess;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ApplicationRequest {

    private Applicant applicant;

    private RecruitmentProcess recruitmentProcess;
}
