package io.github.atifmunawar34.taskflow.task;

import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

@Service
public class TaskService {

	private final Map<Long, Task> store = new ConcurrentHashMap<>();
	private final AtomicLong sequence = new AtomicLong();

	public List<Task> findAll(TaskStatus status) {
		return store.values().stream()
				.filter(task -> status == null || task.getStatus() == status)
				.sorted(Comparator.comparing(Task::getId))
				.toList();
	}

	public Task findById(Long id) {
		Task task = store.get(id);
		if (task == null) {
			throw new TaskNotFoundException(id);
		}
		return task;
	}

	public Task create(CreateTaskRequest request) {
		Task task = new Task(sequence.incrementAndGet(), request.title(), request.description(),
				TaskStatus.TODO, request.priority(), Instant.now());
		store.put(task.getId(), task);
		return task;
	}

	public Task update(Long id, UpdateTaskRequest request) {
		Task task = findById(id);
		task.setTitle(request.title());
		task.setDescription(request.description());
		task.setStatus(request.status());
		task.setPriority(request.priority());
		return task;
	}

	public void delete(Long id) {
		if (store.remove(id) == null) {
			throw new TaskNotFoundException(id);
		}
	}
}
