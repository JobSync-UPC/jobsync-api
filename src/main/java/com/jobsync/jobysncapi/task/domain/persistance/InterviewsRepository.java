package com.jobsync.jobysncapi.task.domain.persistance;

import com.jobsync.jobysncapi.task.domain.model.entity.Interview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InterviewsRepository extends JpaRepository<Interview, Long> {

}
