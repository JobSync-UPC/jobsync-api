package com.jobsync.jobysncapi.organization.domain.model.entity;


import com.jobsync.jobysncapi.applicant.model.entity.Applicant;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="applications")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long current_application_phase;

    private Date application_date;

    private Boolean is_active;

    @ManyToOne
    @JoinColumn(name = "applicant_id", referencedColumnName = "id")
    private Applicant applicant;

    @ManyToOne
    @JoinColumn(name = "recruitment_processes_id", referencedColumnName = "id")
    private RecruitmentProcess recruitmentProcess;
}
