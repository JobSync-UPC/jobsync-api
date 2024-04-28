package com.jobsync.jobysncapi.task.domain.persistance;

import com.jobsync.jobysncapi.task.domain.model.entity.Evaluations;
import com.jobsync.jobysncapi.task.domain.model.entity.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EvaluationsRepository extends JpaRepository<Evaluations, Long> {

    Optional<Evaluations> findByTitle(String title);

    List<Evaluations> findByTasks(Tasks tasks);

    Boolean existsEvaluationsByTitle(String title);

}
