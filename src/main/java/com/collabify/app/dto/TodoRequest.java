package com.collabify.app.dto;

public record TodoRequest(
  String title,
  String description,
  Boolean completed
) {
  
}
