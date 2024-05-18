package com.jobsync.jobysncapi.organization.domain.persistence;

import com.jobsync.jobysncapi.organization.domain.model.entity.ApplicationPhaseTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationPhaseTaskRepository extends JpaRepository<ApplicationPhaseTask, Long> {
}
