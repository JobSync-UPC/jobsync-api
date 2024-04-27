package com.jobsync.jobysncapi.task.api.rest;

import com.jobsync.jobysncapi.task.api.dto.request.TaskRequest;
import com.jobsync.jobysncapi.task.api.dto.request.UpdateTaskRequest;
import com.jobsync.jobysncapi.task.domain.model.entity.Tasks;
import com.jobsync.jobysncapi.task.service.TasksService;
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
    private TasksService tasksService;

    @Operation(summary = "Get all tasks")
    @Transactional(readOnly = true)
    @GetMapping("/")
    public Iterable<Tasks> getAllTasks() {
        return tasksService.getAllTasks();
    }

    @Operation(summary = "Get tasks by id")
    @Transactional(readOnly = true)
    @GetMapping("/{id}")
    public Tasks getTaskById(@PathVariable Long id) {
        return tasksService.getTasksById(id);
    }

    @Operation(summary = "Create a new task")
    @Transactional
    @PostMapping("/")
    public Tasks createTask(@RequestBody TaskRequest taskRequest) {
        return tasksService.createTask(taskRequest);
    }

    @Operation(summary = "Update a task")
    @Transactional
    @PutMapping("/{id}")
    public Tasks updateTask(@RequestBody UpdateTaskRequest updateTaskRequest) {
        return tasksService.updateTask(updateTaskRequest);
    }

    @Operation(summary = "Delete a evaluation")
    @Transactional
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        tasksService.deleteTask(id);
    }
}
