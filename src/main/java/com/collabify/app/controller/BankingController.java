package com.collabify.app.controller;

import com.collabify.app.dto.transaction.TransactionRequest;
import com.collabify.app.dto.transaction.TransactionResponse;
import com.collabify.app.model.Account;
import com.collabify.app.model.Transaction;
import com.collabify.app.service.AccountService;
import com.collabify.app.service.TransactionService;
import com.collabify.app.service.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/api/banking")
public class BankingController {
  @Autowired UserService userService;
  @Autowired AccountService accountService;
  @Autowired TransactionService transactionService;

  @PostMapping("/transactions/transfer")
  public ResponseEntity<TransactionResponse> transfer(
    @RequestParam Long fromAccountId, 
    @RequestParam Long toAccountId, 
    @RequestParam double amount
  ) {
    TransactionResponse transactionCreated = accountService.transfer(fromAccountId, toAccountId, amount);
    return ResponseEntity.status(201).body(transactionCreated);
  }

  @GetMapping("/transactions")
  public List<TransactionResponse> getTransactionsFromAccountId() {
    return transactionService
      .listTransactions()
      .stream()
      .map(TransactionResponse::from)
      .toList();
  }

  @GetMapping("/accounts/{accountId}/transactions")
  public List<TransactionResponse> getTransactionsFromAccountId(@PathVariable Long accountId) {
    Account accountFrom = accountService.getAccountById(accountId);
    return transactionService
      .listAccountTransactions(accountFrom)
      .stream()
      .map(TransactionResponse::from)
      .toList();
  }
}
