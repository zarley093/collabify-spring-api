package com.collabify.app.dto.todo;

public record TodoRequest(
  String title,
  String description,
  Boolean completed
) {}
