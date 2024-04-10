package com.jobsync.jobysncapi.security.service.dto;

import com.jobsync.jobysncapi.applicant.model.entity.Applicant;
import com.jobsync.jobysncapi.recruiter.domain.model.entity.Recruiter;
import com.jobsync.jobysncapi.security.domain.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponseDto {
    private String token;
    private User user;
    private Applicant applicant;
    private Recruiter recruiter;
}