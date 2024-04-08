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
@Table(name="application_phase")
public class ApplicationPhase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "applications_id", referencedColumnName = "id")
    private Application application;


    @ManyToOne
    @JoinColumn(name = "recruitment_phases_id", referencedColumnName = "id")
    private RecruitmentPhase recruitmentPhase;

}
