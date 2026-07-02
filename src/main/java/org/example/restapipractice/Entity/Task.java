package org.example.restapipractice.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "completed")
    private boolean completed;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    @Column(name = "duedate")
    private String dueDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User user;

    //private List<Task> tasks = new ArrayList<>();
//
//    public void addTask (Task task) {
//        this.tasks.add(task);
//    }


    }


