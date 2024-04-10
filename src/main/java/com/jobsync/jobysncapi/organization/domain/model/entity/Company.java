package com.jobsync.jobysncapi.organization.domain.model.entity;

import com.jobsync.jobysncapi.recruiter.domain.model.entity.Recruiter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="companies", uniqueConstraints = {
        @UniqueConstraint(columnNames = "name")
})
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String country;

    private String address;

    private String logoUrl;

    private String website;

    private String industry;

    private Long recruiter_owner_id;

    private Integer subscription_id;

    private Boolean enabled = true;

    @OneToMany(mappedBy = "company")
    List<Recruiter> recruiters;
}
