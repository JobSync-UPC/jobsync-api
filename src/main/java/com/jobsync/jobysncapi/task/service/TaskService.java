package com.jobsync.jobysncapi.task.service;


import com.jobsync.jobysncapi.task.api.dto.request.TaskRequest;
import com.jobsync.jobysncapi.task.api.dto.request.UpdateTaskRequest;
import com.jobsync.jobysncapi.task.domain.model.entity.Task;

public interface TaskService {
    public abstract Task createTask(TaskRequest taskRequest);
    public abstract Task updateTask(UpdateTaskRequest updateTaskRequest);
    public abstract void deleteTask(Long taskId);
    public Iterable<Task> getAllTasks();
    public abstract Task getTasksById(Long taskId);
}
