package org.example.restapipractice.DTO;

public class CreateTaskRequest {

    private String name;
    private String description;
    private String dueDate;


    public CreateTaskRequest(String name, String description, String dueDate) {
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
    }
}
