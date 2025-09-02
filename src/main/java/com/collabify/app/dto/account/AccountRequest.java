package com.collabify.app.dto.account;

import java.util.List;

import com.collabify.app.model.Transaction;
import com.collabify.app.model.User;

public record AccountRequest(
  String type,
  Double balance,
  Long userId,
  List<Transaction> outgoingTransactions,
  List<Transaction> incomingTransactions
) {}
