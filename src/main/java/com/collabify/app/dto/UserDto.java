// src/main/java/com/example/user/web/dto/UserSummary.java
package com.collabify.app.dto;

public class UserDto {
  private final Long id;
  private final String username;

  public UserDto(Long id, String username) {
    this.id = id;
    this.username = username;
  }
  // public Long getId() { return id; }
  // public String getUsername() { return username; }
}