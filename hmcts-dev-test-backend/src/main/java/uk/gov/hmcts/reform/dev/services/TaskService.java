package uk.gov.hmcts.reform.dev.services;

import org.springframework.stereotype.Service;
import uk.gov.hmcts.reform.dev.models.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TaskService {

    private final Map<Long, Task> tasks = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong();

    public Task createTask(Task task) {
        task.setId(idCounter.incrementAndGet());
        tasks.put(task.getId(), task);
        return task;
    }

    public Task getTaskById(Long id) {
        return tasks.get(id);
    }

    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    public Task updateTaskStatus(Long id, String newStatus) {
        Task task = tasks.get(id);
        if (task != null) {
            task.setStatus(newStatus);
        }
        return task;
    }

    public void deleteTask(Long id) {
        tasks.remove(id);
    }
}
