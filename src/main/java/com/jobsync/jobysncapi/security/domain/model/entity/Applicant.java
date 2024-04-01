package com.jobsync.jobysncapi.security.domain.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="applicants")
public class Applicant extends User {
    private String cvUrl;
    private String linkedInUrl;
    private String portfolioUrl;
}
