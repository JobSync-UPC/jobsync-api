package com.jobsync.jobysncapi.organization.domain.model.entity;

import com.jobsync.jobysncapi.security.domain.model.entity.Recruiter;
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
    Integer id;

    String name;

    String description;

    String logoUrl;

    String website;

    String industry;

    @OneToMany(mappedBy = "company")
    List<Recruiter> recruiters;
}
