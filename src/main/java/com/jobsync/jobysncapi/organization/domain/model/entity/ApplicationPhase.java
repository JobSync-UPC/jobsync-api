package com.jobsync.jobysncapi.organization.domain.model.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="application_phase")
public class ApplicationPhase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "applications_id", referencedColumnName = "id")
    private Application application;


    @ManyToOne
    @JoinColumn(name = "recruitment_phases_id", referencedColumnName = "id")
    private RecruitmentPhase recruitmentPhase;

    @OneToMany(mappedBy = "applicationPhase")
    private List<ApplicationPhaseTask> applicationPhaseTasks;


}
