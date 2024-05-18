package com.jobsync.jobysncapi.organization.domain.persistence;

import com.jobsync.jobysncapi.organization.domain.model.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
}
