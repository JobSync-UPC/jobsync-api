package com.jobsync.jobysncapi.organization.api.dto.response;

import com.jobsync.jobysncapi.applicant.model.entity.Applicant;
import com.jobsync.jobysncapi.organization.domain.model.entity.RecruitmentPhase;
import com.jobsync.jobysncapi.organization.domain.model.entity.RecruitmentProcess;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
public class ApplicationResponse {
    private Long id;
    private Date application_date;
    private Boolean is_active;
    private Applicant applicant;
    private RecruitmentProcess recruitmentProcess;
    private RecruitmentPhase currentRecruitmentPhase;
}
