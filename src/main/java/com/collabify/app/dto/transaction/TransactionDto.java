package com.collabify.app.dto.transaction;

import java.time.Instant;

import com.collabify.app.model.Account;

public class TransactionDto {
  private final Long id;
  private final String type;
  private final Instant timestamp;
  private final Account fromAccount;
  private final Account toAccount;

  public TransactionDto(
    Long id, 
    String type, 
    Instant timestamp, 
    Account fromAccount,
    Account toAccount
  ) {
    this.id = id;
    this.type = type;
    this.timestamp = timestamp;
    this.fromAccount = fromAccount;
    this.toAccount = toAccount;

  }
  // public Long getId() { return id; }
  // public String getUsername() { return username; }
}
