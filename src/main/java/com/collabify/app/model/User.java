package com.collabify.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity
public class User {
  @Id 
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Long id;

  @NotNull
  public String username;
  
  @NotNull
  public String email;

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
