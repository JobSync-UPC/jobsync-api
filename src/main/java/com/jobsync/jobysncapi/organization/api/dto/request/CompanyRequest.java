package com.jobsync.jobysncapi.organization.api.dto.request;

import com.jobsync.jobysncapi.security.domain.model.entity.Recruiter;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
public class CompanyRequest {


    private String name;
    private String description;
    private String country;
    private String address;
    private String logoUrl;
    private String website;
    private String industry;

}
