package com.jobsync.jobysncapi.task.domain.model.entity;

import com.jobsync.jobysncapi.organization.domain.model.entity.RecruitmentPhase;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tasks", uniqueConstraints = {
        @UniqueConstraint(columnNames = "title")
})
public class Tasks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 50)
    private String title;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 250)
    private String description;

    @OneToOne
    @JoinColumn(name = "interviews_id", referencedColumnName = "id")
    private Interviews interviews;

    @OneToOne
    @JoinColumn(name = "evaluations_id", referencedColumnName = "id")
    private Evaluations evaluations;

    @ManyToOne
    @JoinColumn(name = "recruitment_phases", referencedColumnName = "id")
    private RecruitmentPhase recruitmentPhase;

}
