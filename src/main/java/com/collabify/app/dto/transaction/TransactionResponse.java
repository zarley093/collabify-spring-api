package com.collabify.app.dto.transaction;

import java.time.Instant;

import com.collabify.app.model.Account;
import com.collabify.app.model.Transaction;

public record TransactionResponse(
  Long id, 
  Double amount,
  String type,
  Instant timestamp,
  Account fromAccount,
  Account toAccount
) {
  public static TransactionResponse from (Transaction transaction) {
    return new TransactionResponse(transaction.getId(), transaction.getAmount(), transaction.getType(), transaction.getTimestamp(), transaction.getFromAccount(), transaction.getToAccount());
  }
} 
