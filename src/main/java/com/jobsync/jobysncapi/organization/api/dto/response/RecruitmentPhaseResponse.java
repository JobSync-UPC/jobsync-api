package com.jobsync.jobysncapi.organization.api.dto.response;

import lombok.Data;

import java.util.Date;

@Data
public class RecruitmentPhaseResponse {

    private Long id;
    private Date startDate;
    private Date endDate;
    private String title;
    private String description;
    private Long recruitmentProcessId;
}
