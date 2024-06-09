package com.jobsync.jobysncapi.organization.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class RecruitmentPhaseRequest {

    @NotNull
    private Date startDate;

    @NotNull
    private Date endDate;

    @NotBlank
    private String title;

    private String description;

    @NotNull
    private Long recruitmentProcessId;

}
