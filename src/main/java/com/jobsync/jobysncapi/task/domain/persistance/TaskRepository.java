package com.jobsync.jobysncapi.task.domain.persistance;

import com.jobsync.jobysncapi.task.domain.model.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

    Task findByInterviewId(Long interviewId);
    Task findByEvaluationId(Long evaluationId);
}
