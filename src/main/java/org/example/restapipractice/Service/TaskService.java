package org.example.restapipractice.Service;

import org.example.restapipractice.Entity.Task;
import org.example.restapipractice.Entity.User;
import org.example.restapipractice.Repository.TaskRepository;
import org.example.restapipractice.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById (Long id) {
        return taskRepository.findById(id);
    }


    public Task createTask(@RequestBody Task task) {
        task.setCreatedAt(LocalDateTime.now());
       return taskRepository.save(task);
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
}
