package com.jobsync.jobysncapi.task.domain.persistance;

import com.jobsync.jobysncapi.task.domain.model.entity.Interviews;
import com.jobsync.jobysncapi.task.domain.model.entity.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TasksRepository extends JpaRepository<Tasks, Long> {

    Tasks findByInterviewId(Long interviewId);
    Tasks findByEvaluationId(Long evaluationId);
    List<Tasks> findByInterviews(Interviews interviews);



}
