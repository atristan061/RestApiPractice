package org.example.restapipractice.Service;

import org.example.restapipractice.DTO.CreateTaskRequest;
import org.example.restapipractice.DTO.TaskResponse;
import org.example.restapipractice.DTO.UpdateTaskRequest;
import org.example.restapipractice.Entity.Task;
import org.example.restapipractice.Entity.User;
import org.example.restapipractice.Repository.TaskRepository;
import org.example.restapipractice.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {


    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Autowired
    public TaskService (TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }


    public List<Task> getAllTasks() {


        return taskRepository.findAll().stream()
                .collect(Collectors.toList());
    }

    // For a GET request, client does NOT usually send a request DTO
    public TaskResponse getTaskById (Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow();



        return new TaskResponse(task.getName(),
                task.getDescription(), task.getDueDate(),
                task.isCompleted(), task.getId());
    }


    public TaskResponse createTask(CreateTaskRequest taskRequest) {
       User user = userRepository.findById(taskRequest.userId())
               .orElseThrow();
       Task task = new Task();
       task.setName(taskRequest.name());
       task.setDescription(taskRequest.description());
       task.setDueDate(taskRequest.dueDate());
       task.setCompleted(false);
       task.setUser(user);

       Task taskSaved = taskRepository.save(task);
       return new TaskResponse(taskSaved.getName(), taskSaved.getDescription(),
               taskSaved.getDueDate(), taskSaved.isCompleted(),
               taskSaved.getUser().getId());
    }

    public List<Task> findAllByUser(User user) {

        return taskRepository.findAllByUser(user);
    }

    public Task findTaskByUser(Long userId) {
        return taskRepository.findTaskByUser(userId);
    }

    public Task createTaskPerUser(Long userId, Task task) {
        Optional<User> user = userRepository.findById(userId);

        if (user.isPresent()) {
            User taskuser = user.get();
            task.setUser(taskuser);

        } else
            System.out.println("Cannot find user");
        task.setCompleted(false);
        task.setCreatedAt(LocalDateTime.now());


        return taskRepository.save(task);
    }

    public TaskResponse updateTask(UpdateTaskRequest updateTaskRequest, Long userId) {
        Task task = taskRepository.findTaskByUser(userId);
        task.setDueDate(updateTaskRequest.dueDate());
        task.setName(updateTaskRequest.name());
        task.setDescription(updateTaskRequest.description());
        task.setCompleted(updateTaskRequest.completed());
        taskRepository.save(task);
        return new TaskResponse(task.getName(), task.getDescription(),
                task.getDueDate(), task.isCompleted(), task.getId());

    }
}
