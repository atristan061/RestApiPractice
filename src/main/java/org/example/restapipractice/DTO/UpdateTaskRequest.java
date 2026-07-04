package org.example.restapipractice.DTO;

public record UpdateTaskRequest( String name, String description, boolean completed, String dueDate) {
}
