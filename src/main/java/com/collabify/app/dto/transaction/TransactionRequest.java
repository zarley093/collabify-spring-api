package com.collabify.app.dto.transaction;

import java.time.Instant;

import com.collabify.app.model.Account;

public record TransactionRequest(
  Double amount,
  String type,
  Instant timestamp,
  Account fromAccount,
  Account toAccount
) {}
