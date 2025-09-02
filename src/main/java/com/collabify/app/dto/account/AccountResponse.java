package com.collabify.app.dto.account;

import java.util.List;

import com.collabify.app.model.Account;
import com.collabify.app.model.Transaction;
import com.collabify.app.model.User;

public record AccountResponse(
  Long id, 
  String type, 
  Double balance, 
  User user, 
  List<Transaction> outgoingTransactions, 
  List<Transaction> incomingTransactions
) {
  public static AccountResponse from (Account account) {
    return new AccountResponse(
      account.getId(), 
      account.getType(),
      account.getBalance(), 
      account.getAccountUser(), 
      account.getOutgoingTransactions(),
      account.getIncomingTransactions()
    );
  }
} 
