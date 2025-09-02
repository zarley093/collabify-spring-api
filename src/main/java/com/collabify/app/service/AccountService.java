package com.collabify.app.service;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.collabify.app.model.Account;
import com.collabify.app.model.Transaction;
import com.collabify.app.repository.AccountRepository;
import com.collabify.app.repository.TransactionRepository;

import jakarta.transaction.Transactional;

@Service
public class AccountService {
  @Autowired AccountRepository accountRepository;
  @Autowired TransactionRepository transactionRepository;

  @Transactional
  public Transaction transfer(Long fromAccountId, Long toAccountId, Double amount) {
    Account fromAccount = accountRepository.findById(fromAccountId).orElseThrow(() -> new IllegalArgumentException("User not found"));
    Account toAccount = accountRepository.findById(fromAccountId).orElseThrow(() -> new IllegalArgumentException("User not found"));

    if (fromAccount.getBalance() < amount) {
      throw new IllegalArgumentException("User not found");
    }

    // Deduct and add balances
    fromAccount.setBalance(fromAccount.getBalance() - amount);
    toAccount.setBalance(toAccount.getBalance() + amount);

    // Save updated accounts
    accountRepository.save(fromAccount);
    accountRepository.save(toAccount);

    // Record transaction
    Transaction trx = new Transaction();
    trx.setType("TRANSFER");
    trx.setAmount(amount);
    trx.setFromAccount(fromAccount);
    trx.setToAccount(toAccount);
    trx.setTimestamp(Instant.now());
    return transactionRepository.save(trx);
  }
}
