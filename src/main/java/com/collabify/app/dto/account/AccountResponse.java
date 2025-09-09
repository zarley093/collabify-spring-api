package com.collabify.app.dto.account;

import java.util.List;

import com.collabify.app.dto.transaction.TransactionResponse;
import com.collabify.app.model.Account;

public record AccountResponse(
  Long id,
  String type,
  Double balance,
  Long userId,
  List<TransactionResponse> outgoingTransactions,
  List<TransactionResponse> incomingTransactions
) {
  public static AccountResponse from(Account account) {
    return new AccountResponse(
      account.getId(),
      account.getType(),
      account.getBalance(),
      account.getAccountUser() != null ? account.getAccountUser().getId() : null,
      account.getOutgoingTransactions() != null
        ? account.getOutgoingTransactions().stream().map(TransactionResponse::from).toList()
        : List.of(),
      account.getIncomingTransactions() != null
        ? account.getIncomingTransactions().stream().map(TransactionResponse::from).toList()
        : List.of()
    );
  }
}
