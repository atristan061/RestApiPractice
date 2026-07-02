package org.example.restapipractice.Repository;

import org.example.restapipractice.Entity.Task;
import org.example.restapipractice.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByUser(User user);
    Task findTaskByUser(Long userId);


    Long user(User user);
}
