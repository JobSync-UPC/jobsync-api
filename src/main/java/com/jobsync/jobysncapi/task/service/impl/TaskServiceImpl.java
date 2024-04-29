package com.jobsync.jobysncapi.task.service.impl;

import com.jobsync.jobysncapi.task.api.dto.request.TaskRequest;
import com.jobsync.jobysncapi.task.api.dto.request.UpdateTaskRequest;
import com.jobsync.jobysncapi.task.domain.model.entity.Evaluation;
import com.jobsync.jobysncapi.task.domain.model.entity.Interview;
import com.jobsync.jobysncapi.task.domain.model.entity.Task;
import com.jobsync.jobysncapi.task.domain.persistance.EvaluationRepository;
import com.jobsync.jobysncapi.task.domain.persistance.InterviewsRepository;
import com.jobsync.jobysncapi.task.domain.persistance.TaskRepository;
import com.jobsync.jobysncapi.task.service.TaskService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;
    private final InterviewsRepository interviewsRepository;
    private final EvaluationRepository evaluationRepository;


    @Autowired
    public TaskServiceImpl(InterviewsRepository interviewsRepository, EvaluationRepository evaluationRepository, TaskRepository taskRepository, ModelMapper modelMapper){
        this.taskRepository = taskRepository;
        this.modelMapper = modelMapper;
        this.evaluationRepository = evaluationRepository;
        this.interviewsRepository = interviewsRepository;
    }

    @Override
    public Task createTask(TaskRequest taskRequest){
        if (taskRequest.getInterviewId() != null && taskRepository.findByInterviewId(taskRequest.getInterviewId()) != null) {
            throw new IllegalStateException("The interview is already assigned to another task.");
        }

        if (taskRequest.getEvaluationId() != null && taskRepository.findByEvaluationId(taskRequest.getEvaluationId()) != null) {
            throw new IllegalStateException("The evaluation is already assigned to another task.");
        }

        Interview interview = null;
        if (taskRequest.getInterviewId() != null) {
            interview = interviewsRepository.findById(taskRequest.getInterviewId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid interview ID."));
        }

        Evaluation evaluation = null;
        if (taskRequest.getEvaluationId() != null) {
            evaluation = evaluationRepository.findById(taskRequest.getEvaluationId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid evaluation ID."));
        }

        Task task = modelMapper.map(taskRequest, Task.class);

        task.setInterview(interview);
        task.setEvaluation(evaluation);

        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(UpdateTaskRequest updateTaskRequest) {
        Task existingTask = taskRepository.findById(updateTaskRequest.getTaskId())
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));

        if (updateTaskRequest.getInterviewId() != null && !updateTaskRequest.getInterviewId().equals(existingTask.getInterview().getId())
                && taskRepository.findByInterviewId(updateTaskRequest.getInterviewId()) != null) {
            throw new IllegalStateException("The interview is already assigned to another task.");
        }

        if (updateTaskRequest.getEvaluationId() != null && !updateTaskRequest.getEvaluationId().equals(existingTask.getEvaluation().getId())
                && taskRepository.findByEvaluationId(updateTaskRequest.getEvaluationId()) != null) {
            throw new IllegalStateException("The evaluation is already assigned to another task.");
        }

        if (updateTaskRequest.getTitle() != null) {
            existingTask.setTitle(updateTaskRequest.getTitle());
        }
        if (updateTaskRequest.getDescription() != null) {
            existingTask.setDescription(updateTaskRequest.getDescription());
        }
        if (updateTaskRequest.getInterviewId() != null) {
            existingTask.setInterview(interviewsRepository.findById(updateTaskRequest.getInterviewId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid interview ID.")));
        }
        if (updateTaskRequest.getEvaluationId() != null) {
            existingTask.setEvaluation(evaluationRepository.findById(updateTaskRequest.getEvaluationId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid evaluation ID.")));
        }

        return taskRepository.save(existingTask);
    }

    @Override
    public void deleteTask(Long taskId){
        if (!taskRepository.existsById(taskId)) {
            throw new EntityNotFoundException("Task not found");
        }
        taskRepository.deleteById(taskId);
    }

    @Override
    public Iterable<Task> getAllTasks(){ return taskRepository.findAll(); }

    @Override
    public Task getTasksById(Long taskId){

        return taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task not found"));
    }


}



