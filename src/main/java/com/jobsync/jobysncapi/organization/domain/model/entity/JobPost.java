package com.jobsync.jobysncapi.organization.domain.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="job_posts")
public class JobPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String description;

    private Boolean enabled = true;

    @OneToOne
    @JoinColumn(name = "recruitment_processes_id", referencedColumnName = "id")
    private RecruitmentProcess recruitmentProcess;
}
