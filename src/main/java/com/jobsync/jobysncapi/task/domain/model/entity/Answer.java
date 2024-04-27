package com.jobsync.jobysncapi.task.domain.model.entity;

import com.jobsync.jobysncapi.organization.domain.model.entity.Company;
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
@Table(name = "answer")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 250)
    private String description;

    @ManyToOne
    @JoinColumn(name = "prompt_id", referencedColumnName = "id")
    private Prompt prompt;


}
