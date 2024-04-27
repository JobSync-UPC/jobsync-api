package com.jobsync.jobysncapi.task.service.impl;

import com.jobsync.jobysncapi.task.api.dto.request.TaskRequest;
import com.jobsync.jobysncapi.task.api.dto.request.UpdateTaskRequest;
import com.jobsync.jobysncapi.task.domain.model.entity.Evaluations;
import com.jobsync.jobysncapi.task.domain.model.entity.Interviews;
import com.jobsync.jobysncapi.task.domain.model.entity.Tasks;
import com.jobsync.jobysncapi.task.domain.persistance.EvaluationsRepository;
import com.jobsync.jobysncapi.task.domain.persistance.InterviewsRepository;
import com.jobsync.jobysncapi.task.domain.persistance.TasksRepository;
import com.jobsync.jobysncapi.task.service.TasksService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TasksServiceImpl implements TasksService {
    private final TasksRepository tasksRepository;
    private final ModelMapper modelMapper;
    private final InterviewsRepository interviewsRepository;
    private final EvaluationsRepository evaluationsRepository;


    @Autowired
    public TasksServiceImpl(InterviewsRepository interviewsRepository, EvaluationsRepository evaluationsRepository, TasksRepository tasksRepository, ModelMapper modelMapper){
        this.tasksRepository = tasksRepository;
        this.modelMapper = modelMapper;
        this.evaluationsRepository = evaluationsRepository;
        this.interviewsRepository = interviewsRepository;
    }

    @Override
    public Tasks createTask(TaskRequest taskRequest){
        if (taskRequest.getInterviewId() != null && tasksRepository.findByInterviewId(taskRequest.getInterviewId()) != null) {
            throw new IllegalStateException("The interview is already assigned to another task.");
        }

        if (taskRequest.getEvaluationId() != null && tasksRepository.findByEvaluationId(taskRequest.getEvaluationId()) != null) {
            throw new IllegalStateException("The evaluation is already assigned to another task.");
        }

        Interviews interview = null;
        if (taskRequest.getInterviewId() != null) {
            interview = interviewsRepository.findById(taskRequest.getInterviewId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid interview ID."));
        }

        Evaluations evaluation = null;
        if (taskRequest.getEvaluationId() != null) {
            evaluation = evaluationsRepository.findById(taskRequest.getEvaluationId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid evaluation ID."));
        }

        Tasks task = modelMapper.map(taskRequest, Tasks.class);

        task.setInterviews(interview);
        task.setEvaluations(evaluation);

        return tasksRepository.save(task);
    }

    @Override
    public Tasks updateTask(UpdateTaskRequest updateTaskRequest) {
        Tasks existingTask = tasksRepository.findById(updateTaskRequest.getTaskId())
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));

        if (updateTaskRequest.getInterviewId() != null && !updateTaskRequest.getInterviewId().equals(existingTask.getInterviews().getId())
                && tasksRepository.findByInterviewId(updateTaskRequest.getInterviewId()) != null) {
            throw new IllegalStateException("The interview is already assigned to another task.");
        }

        if (updateTaskRequest.getEvaluationId() != null && !updateTaskRequest.getEvaluationId().equals(existingTask.getEvaluations().getId())
                && tasksRepository.findByEvaluationId(updateTaskRequest.getEvaluationId()) != null) {
            throw new IllegalStateException("The evaluation is already assigned to another task.");
        }

        if (updateTaskRequest.getTitle() != null) {
            existingTask.setTitle(updateTaskRequest.getTitle());
        }
        if (updateTaskRequest.getDescription() != null) {
            existingTask.setDescription(updateTaskRequest.getDescription());
        }
        if (updateTaskRequest.getInterviewId() != null) {
            existingTask.setInterviews(interviewsRepository.findById(updateTaskRequest.getInterviewId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid interview ID.")));
        }
        if (updateTaskRequest.getEvaluationId() != null) {
            existingTask.setEvaluations(evaluationsRepository.findById(updateTaskRequest.getEvaluationId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid evaluation ID.")));
        }

        return tasksRepository.save(existingTask);
    }

    @Override
    public void deleteTask(Long taskId){
        if (!tasksRepository.existsById(taskId)) {
            throw new EntityNotFoundException("Task not found");
        }
        tasksRepository.deleteById(taskId);
    }

    @Override
    public Iterable<Tasks> getAllTasks(){ return tasksRepository.findAll(); }

    @Override
    public Tasks getTasksById(Long taskId){

        return tasksRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task not found"));
    }


}



