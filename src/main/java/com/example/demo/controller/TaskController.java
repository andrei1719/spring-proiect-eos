package com.example.demo.controller;

import com.example.demo.exceptions.ResourceNotFoundeException;
import com.example.demo.model.Task;
import com.example.demo.model.TaskStatus;
import com.example.demo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/v1")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

//    @GetMapping("/tasks")
//    public List<Task> getAllTasks() {
//        return taskRepository.findAll();
//    }

    @PostMapping("/tasks")
    public Task createTask(@RequestBody Task task) {
        return taskRepository.save(task);
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> getTasks(
            @RequestParam(required = false) Long assigned,
            @RequestParam(required = false) String subject,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dueDate,
            @RequestParam(required = false) TaskStatus status) {

        Sort sort = Sort.by(Sort.Direction.DESC, "dueDate");

        List<Task> tasks = taskRepository.findByAssignedAndSubjectAndDueDateAndStatus(assigned, subject, dueDate, status, sort);
        return ResponseEntity.ok(tasks);
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task taskDetails) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundeException("Task does not exist with id :" + id));

        task.setStatus(taskDetails.getStatus());
        task.setSubject(taskDetails.getSubject());
        task.setDueDate(taskDetails.getDueDate());
        task.setUserID(taskDetails.getUserID());

        Task updatedTask = taskRepository.save(task);
        return ResponseEntity.ok(updatedTask);
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundeException("Task not found with id: " + id));
        return ResponseEntity.ok(task);
    }

    @DeleteMapping("tasks/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteTask(@PathVariable Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundeException("Task does not exist with id :" + id));

        taskRepository.delete(task);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }


}
