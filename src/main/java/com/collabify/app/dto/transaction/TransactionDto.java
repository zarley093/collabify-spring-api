package com.collabify.app.dto.transaction;

import java.time.Instant;

import com.collabify.app.model.Account;

public class TransactionDto {
  private final Long id;
  private final String type;
  private final Instant timestamp;

  public TransactionDto(
    Long id, 
    String type, 
    Instant timestamp 
  ) {
    this.id = id;
    this.type = type;
    this.timestamp = timestamp;
  }
  // public Long getId() { return id; }
  // public String getUsername() { return username; }
}
