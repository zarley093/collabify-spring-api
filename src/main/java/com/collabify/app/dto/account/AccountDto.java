package com.collabify.app.dto.account;

import java.util.List;

import com.collabify.app.model.Transaction;
import com.collabify.app.model.User;

public class AccountDto {
  private final Long id;
  private final String type;
  private final Double balance;
  private final User user;
  private final List<Transaction> outgoingTransactions;
  private final List<Transaction> incomingTransactions;

  public AccountDto(Long id, String type, Double balance, User user, List<Transaction> outgoingTransactions, List<Transaction> incomingTransactions) {
    this.id = id;
    this.type = type;
    this.balance = balance;
    this.user = user;
    this.outgoingTransactions = outgoingTransactions;
    this.incomingTransactions = incomingTransactions;

  }
  // public Long getId() { return id; }
  // public String getUsername() { return username; }
}
