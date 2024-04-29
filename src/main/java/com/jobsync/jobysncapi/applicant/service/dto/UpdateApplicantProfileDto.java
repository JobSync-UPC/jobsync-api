package com.jobsync.jobysncapi.applicant.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateApplicantProfileDto {
    private String cvUrl;
    private String linkedInUrl;
    private String portfolioUrl;
}
