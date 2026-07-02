package org.example.restapipractice.Controllers;

import org.example.restapipractice.Entity.Task;
import org.example.restapipractice.Repository.TaskRepository;
import org.example.restapipractice.Repository.UserRepository;
import org.example.restapipractice.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {


    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private TaskService taskService;


    @Autowired
    public TaskController(TaskService taskService, UserRepository userRepository, TaskRepository taskRepository) {
        this.taskService = taskService;
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    @GetMapping("/")
    public List<Task> getAllTasks() {
        return new ArrayList<>();
    }

    @GetMapping("/{id}")
    public Optional<Task> getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @PostMapping("/users/{userId}")
    public Task createTaskPerUser(@PathVariable
                                  Long userId, @RequestBody Task task) {
        System.out.println("Controller Reached");
       return taskService.createTaskPerUser(userId, task);
    }


}




