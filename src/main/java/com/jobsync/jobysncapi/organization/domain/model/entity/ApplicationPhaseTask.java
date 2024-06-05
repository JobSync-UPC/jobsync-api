//package com.jobsync.jobysncapi.organization.domain.model.entity;
//
//import com.jobsync.jobysncapi.task.domain.model.entity.Task;
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//
//@Data
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@Table(name = "application_phase_task")
//public class ApplicationPhaseTask {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "application_phase_id", referencedColumnName = "id")
//    private ApplicationPhase applicationPhase;
//
//    @ManyToOne
//    @JoinColumn(name = "task_id", referencedColumnName = "id")
//    private Task task;
//
//    private boolean completed;
//    private boolean pass;
//}
