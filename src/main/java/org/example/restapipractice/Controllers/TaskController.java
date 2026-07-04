package org.example.restapipractice.Controllers;

import org.example.restapipractice.DTO.CreateTaskRequest;
import org.example.restapipractice.DTO.TaskResponse;
import org.example.restapipractice.DTO.UpdateTaskRequest;
import org.example.restapipractice.Entity.Task;
import org.example.restapipractice.Repository.TaskRepository;
import org.example.restapipractice.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskRepository taskRepository;
    private TaskService taskService;
    private CreateTaskRequest taskRequest;


    @Autowired
    public TaskController(TaskService taskService, TaskRepository taskRepository) {
        this.taskService = taskService;

        this.taskRepository = taskRepository;
    }

    @GetMapping("/")
    public List<Task> getAllTasks() {
        return new ArrayList<>();
    }

    @GetMapping("/{id}")
    public TaskResponse getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@RequestBody CreateTaskRequest taskRequest) {
        TaskResponse taskResponse = taskService.createTask(taskRequest);


        return ResponseEntity.status(HttpStatus.CREATED)
                .body(taskResponse);
    }

    @PostMapping("/users/{userId}")
    public Task createTaskPerUser(@PathVariable
                                  Long userId, @RequestBody Task task) {
        System.out.println("Controller Reached");
       return taskService.createTaskPerUser(userId, task);
    }


    @PatchMapping("/users/{userId}")
    public ResponseEntity<TaskResponse> updateTask (
            @RequestBody UpdateTaskRequest updateTaskRequest,@PathVariable Long userId) {
        //TaskResponse taskResponse = taskService.updateTask(updateTaskRequest);
        return ResponseEntity.ok(taskService.updateTask(updateTaskRequest, userId));
    }

}




