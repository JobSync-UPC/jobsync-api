package com.jobsync.jobysncapi.organization.domain.model.entity;


import com.jobsync.jobysncapi.applicant.model.entity.Applicant;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.EnableMBeanExport;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="applications", uniqueConstraints = {
        @UniqueConstraint(columnNames = "name")
})
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer current_application_phase;

    @ManyToOne
    @JoinColumn(name = "applicant_id", referencedColumnName = "id")
    private Applicant applicant;

    @ManyToOne
    @JoinColumn(name = "recruitment_processes_id", referencedColumnName = "id")
    private RecruitmentProcess recruitmentProcess;



}
