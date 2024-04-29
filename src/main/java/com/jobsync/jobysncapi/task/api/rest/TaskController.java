package com.jobsync.jobysncapi.task.api.rest;

import com.jobsync.jobysncapi.task.api.dto.request.TaskRequest;
import com.jobsync.jobysncapi.task.api.dto.request.UpdateTaskRequest;
import com.jobsync.jobysncapi.task.domain.model.entity.Task;
import com.jobsync.jobysncapi.task.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Task Controller", description = "Create, read, update, and delete tasks")
@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Operation(summary = "Get all tasks")
    @Transactional(readOnly = true)
    @GetMapping("/")
    public Iterable<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @Operation(summary = "Get tasks by id")
    @Transactional(readOnly = true)
    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) {
        return taskService.getTasksById(id);
    }

    @Operation(summary = "Create a new task")
    @Transactional
    @PostMapping("/")
    public Task createTask(@RequestBody TaskRequest taskRequest) {
        return taskService.createTask(taskRequest);
    }

    @Operation(summary = "Update a task")
    @Transactional
    @PutMapping("/{id}")
    public Task updateTask(@RequestBody UpdateTaskRequest updateTaskRequest) {
        return taskService.updateTask(updateTaskRequest);
    }

    @Operation(summary = "Delete a evaluation")
    @Transactional
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
}
