package com.jobsync.jobysncapi.organization.domain.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="recruitment_processes")
public class RecruitmentProcess {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Company company;

    @OneToMany(mappedBy = "recruitmentProcess")
    private List<RecruitmentPhase> recruitmentPhases;

    @OneToOne
    @JoinColumn(name = "job_post_id", referencedColumnName = "id")
    private JobPost jobPost;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private Date created_date;

    private Boolean enabled = true;
}
