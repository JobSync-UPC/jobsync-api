package com.jobsync.jobysncapi.task.domain.persistance;

import com.jobsync.jobysncapi.task.domain.model.entity.Interviews;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InterviewsRepository extends JpaRepository<Interviews, Long> {
    Optional<Interviews> findByTitle(String title);

    Boolean existsInterviewsByTitle(String title);
}
