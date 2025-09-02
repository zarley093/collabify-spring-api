package com.collabify.app.dto.todo;

public class TodoDto {
  private final Long id;
  private final String title;
  private final String description;
  private final Boolean completed;

  public TodoDto(Long id, String title, String description, Boolean completed) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.completed = completed;

  }
  // public Long getId() { return id; }
  // public String getUsername() { return username; }
}
