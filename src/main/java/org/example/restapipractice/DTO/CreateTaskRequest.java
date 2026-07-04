package org.example.restapipractice.DTO;

public record CreateTaskRequest (
     String name,
     String description,
     String dueDate,
     Long userId)
{



}
