package com.jobsync.jobysncapi.organization.api.dto.response;

import lombok.Data;

@Data
public class ApplicationResponse {

    private Long id;
    private Long applicantId;
    private Long recruitmentProcessId;
    private Long currentApplicationPhaseId;
}
