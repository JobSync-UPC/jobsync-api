package com.jobsync.jobysncapi.organization.api.dto.response;

import com.jobsync.jobysncapi.security.domain.model.entity.Recruiter;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;
@Data
public class CompanyResponse {

    private Long id;
    private String name;
    private String description;
    private String country;
    private String address;
    private String logoUrl;
    private String website;
    private String industry;
    private Integer recruiter_owner_id;
    private Integer subscription_id;
    private Boolean enabled;

    @OneToMany(mappedBy = "company")
    List<Recruiter> recruiters;
}
