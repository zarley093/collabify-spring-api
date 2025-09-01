package com.collabify.app.dto;

import com.collabify.app.model.Todo;

public record TodoResponse(Long id, String title, String description, Boolean completed) {
  public static TodoResponse from (Todo todo) {
    return new TodoResponse(todo.getId(), todo.getTitle(), todo.getDescription(), todo.getCompleted());
  }
} 
