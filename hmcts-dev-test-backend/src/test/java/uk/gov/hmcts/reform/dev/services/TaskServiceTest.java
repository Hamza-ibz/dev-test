package uk.gov.hmcts.reform.dev.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.gov.hmcts.reform.dev.models.Task;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskServiceTest {

    private TaskService taskService;

    @BeforeEach
    void setUp() {
        taskService = new TaskService();
    }

    @Test
    void shouldCreateTaskSuccessfully() {
        Task task = new Task(null, "Test Title", "Test Description", "Pending", LocalDateTime.now());
        Task createdTask = taskService.createTask(task);

        assertNotNull(createdTask.getId());
        assertEquals("Test Title", createdTask.getTitle());
    }

    @Test
    void shouldRetrieveTaskById() {
        Task task = new Task(null, "Test Title", "Test Description", "Pending", LocalDateTime.now());
        Task createdTask = taskService.createTask(task);

        Task retrievedTask = taskService.getTaskById(createdTask.getId());

        assertEquals(createdTask.getId(), retrievedTask.getId());
        assertEquals(createdTask.getTitle(), retrievedTask.getTitle());
    }

    @Test
    void shouldGetAllTasks() {
        Task task1 = new Task(null, "Task 1", "Desc 1", "Pending", LocalDateTime.now());
        Task task2 = new Task(null, "Task 2", "Desc 2", "In Progress", LocalDateTime.now());

        taskService.createTask(task1);
        taskService.createTask(task2);

        List<Task> allTasks = taskService.getAllTasks();

        assertEquals(2, allTasks.size());
    }

    @Test
    void shouldUpdateTaskStatus() {
        Task task = new Task(null, "Test Title", "Test Description", "Pending", LocalDateTime.now());
        Task createdTask = taskService.createTask(task);

        Task updatedTask = taskService.updateTaskStatus(createdTask.getId(), "Completed");

        assertEquals("Completed", updatedTask.getStatus());
    }

    @Test
    void shouldDeleteTask() {
        Task task = new Task(null, "Test Title", "Test Description", "Pending", LocalDateTime.now());
        Task createdTask = taskService.createTask(task);

        taskService.deleteTask(createdTask.getId());

        assertNull(taskService.getTaskById(createdTask.getId()));
    }
}
