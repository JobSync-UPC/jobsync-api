package com.jobsync.jobysncapi.task.service;


import com.jobsync.jobysncapi.task.api.dto.request.TaskRequest;
import com.jobsync.jobysncapi.task.api.dto.request.UpdateTaskRequest;
import com.jobsync.jobysncapi.task.domain.model.entity.Tasks;

public interface TasksService {
    public abstract Tasks createTask(TaskRequest taskRequest);
    public abstract Tasks updateTask(UpdateTaskRequest updateTaskRequest);
    public abstract void deleteTask(Long taskId);
    public Iterable<Tasks> getAllTasks();
    public abstract Tasks getTasksById(Long taskId);
}
