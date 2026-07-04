package org.example.restapipractice.DTO;

public record TaskResponse(String name, String description, String dueDate,
                           boolean completed, Long id){
    
}