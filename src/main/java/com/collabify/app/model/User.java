package com.collabify.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {
  @Id 
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Long id;
  public String username;
  public String email;
  public void setUsername(String username2) {
    this.username = username2;
  }
  public void setEmail(String email2) {
    this.email = email2;
  }
}
