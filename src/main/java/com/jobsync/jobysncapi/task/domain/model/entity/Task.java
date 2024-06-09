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

import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tasks", uniqueConstraints = {
        @UniqueConstraint(columnNames = "title")
})
public class Task {
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
    @JoinColumn(name = "interview_id", referencedColumnName = "id")
    private Interview interview;

    @OneToOne
    @JoinColumn(name = "evaluations_id", referencedColumnName = "id")
    private Evaluation evaluation;

    @ManyToOne
    @JoinColumn(name = "recruitment_phases", referencedColumnName = "id")
    private RecruitmentPhase recruitmentPhase;

//    @OneToMany(mappedBy = "task")
//    private List<ApplicationPhaseTask> applicationPhaseTasks;
}
