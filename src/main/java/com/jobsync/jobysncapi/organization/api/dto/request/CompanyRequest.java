package com.jobsync.jobysncapi.organization.api.dto.request;


import lombok.Data;


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
