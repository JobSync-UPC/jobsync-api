package com.jobsync.jobysncapi.task.domain.persistance;

import com.jobsync.jobysncapi.task.domain.model.entity.Evaluation;
import com.jobsync.jobysncapi.task.domain.model.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {

}
