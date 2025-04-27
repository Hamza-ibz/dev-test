package uk.gov.hmcts.reform.dev.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.gov.hmcts.reform.dev.models.Task;
import uk.gov.hmcts.reform.dev.services.TaskService;

import java.util.List;

/**
 * REST controller for managing tasks in the HMCTS case management system.
 * Provides endpoints for creating, retrieving, updating, and deleting tasks.
 */
@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * Create a new task.
     *
     * @param task The task object to create
     * @return The created task with a generated ID
     */
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task created = taskService.createTask(task);
        return ResponseEntity.ok(created);
    }

    /**
     * Retrieve a task by its ID.
     *
     * @param id The ID of the task
     * @return The task if found, otherwise a 404 Not Found response
     */
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Task task = taskService.getTaskById(id);
        return task != null ? ResponseEntity.ok(task) : ResponseEntity.notFound().build();
    }

    /**
     * Retrieve all tasks.
     *
     * @return A list of all tasks
     */
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    /**
     * Update the status of a task by its ID.
     *
     * @param id The ID of the task
     * @param status The new status to set (e.g., "Pending", "In Progress", "Completed")
     * @return The updated task if found, otherwise a 404 Not Found response
     */
    @PutMapping("/{id}/status")
    public ResponseEntity<Task> updateTaskStatus(@PathVariable Long id, @RequestBody String status) {
        Task updated = taskService.updateTaskStatus(id, status);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    /**
     * Delete a task by its ID.
     *
     * @param id The ID of the task to delete
     * @return A 204 No Content response if successfully deleted
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
