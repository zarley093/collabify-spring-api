package com.collabify.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity
public class User {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true, length = 50)
  private String username;

  @Column(nullable = false, unique = true, length = 120)
  private String email;

  protected User() {} // JPA no-args

  public User(String username, String email) { // convenience
    this.username = username;
    this.email = email;
  }

  public Long getId() {
    return id;
  }

  public String getEmail() {
   return email;
  }

  public void setEmail(String email2) {
    this.email = email2;
  }

  public String getUsername() {
   return username;
  }
  
  public void setUsername(String username2) {
    this.username = username2;
  }
}
