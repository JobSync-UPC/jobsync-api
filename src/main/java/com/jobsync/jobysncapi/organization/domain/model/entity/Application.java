package com.jobsync.jobysncapi.organization.domain.model.entity;


import com.jobsync.jobysncapi.applicant.model.entity.Applicant;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.Fetch;

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

    private Date application_date;

    private Boolean is_active;

    @ManyToOne
    @JoinColumn(name = "applicant_id", referencedColumnName = "id")
    private Applicant applicant;

    @ManyToOne
    @JoinColumn(name = "recruitment_process_id", referencedColumnName = "id")
    private RecruitmentProcess recruitmentProcess;

    @ManyToOne
    @JoinColumn(name = "recruitment_phase_id", referencedColumnName = "id")
    private RecruitmentPhase currentRecruitmentPhase;
}
