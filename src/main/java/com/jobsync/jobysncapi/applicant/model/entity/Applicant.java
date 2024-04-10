package com.jobsync.jobysncapi.applicant.model.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jobsync.jobysncapi.security.domain.model.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="applicants")
public class Applicant extends User {

    @Fetch(FetchMode.JOIN)
    @Column(name = "cv_url")
    private String cvUrl;

    @Fetch(FetchMode.JOIN)
    @Column(name = "linkedin_url")
    private String linkedInUrl;

    @Fetch(FetchMode.JOIN)
    @Column(name = "portfolio_url")
    private String portfolioUrl;
}
