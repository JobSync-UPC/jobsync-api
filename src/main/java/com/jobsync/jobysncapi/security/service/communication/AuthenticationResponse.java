package com.jobsync.jobysncapi.security.service.communication;

import com.jobsync.jobysncapi.security.domain.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private String token;
    private User user;
}