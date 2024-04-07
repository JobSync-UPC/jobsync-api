package com.jobsync.jobysncapi.security.service.communication;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {
    private String firstname;
    private String lastname;
    private String phoneNumber;
    private String profilePictureUrl;
    private String country;
}
