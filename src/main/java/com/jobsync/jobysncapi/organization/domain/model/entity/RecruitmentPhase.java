package com.jobsync.jobysncapi.organization.domain.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name="recruitment_phases", uniqueConstraints = {
        @UniqueConstraint(columnNames = "name")
})
public class RecruitmentPhase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private Date start_date;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private Date end_date;

    private String title;

    private String description;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "recruitment_processes_id", referencedColumnName = "id")
    private RecruitmentProcess recruitmentProcess;
}