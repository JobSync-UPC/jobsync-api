package com.jobsync.jobysncapi.security.domain.model.entity;

import com.jobsync.jobysncapi.organization.domain.model.entity.Company;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="recruiters")
public class Recruiter extends User {
    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Company company;
}
