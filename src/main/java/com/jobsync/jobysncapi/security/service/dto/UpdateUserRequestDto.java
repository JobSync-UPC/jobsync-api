package com.jobsync.jobysncapi.security.service.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequestDto {
    private String firstname;
    private String lastname;
    private String phoneNumber;
    private String profilePictureUrl;
    private String country;
}
