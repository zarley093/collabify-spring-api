package com.collabify.app.service;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.collabify.app.dto.account.AccountRequest;
import com.collabify.app.dto.account.AccountResponse;
import com.collabify.app.model.Account;
import com.collabify.app.model.Transaction;
import com.collabify.app.model.User;
import com.collabify.app.repository.AccountRepository;
import com.collabify.app.repository.TransactionRepository;
import com.collabify.app.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class AccountService {
  @Autowired 
  private final AccountRepository accountRepository;
  
  @Autowired 
  TransactionRepository transactionRepository;

  @Autowired
  UserRepository userRepository;

  public AccountService(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  public List<Account> listAccounts() {
    return accountRepository.findAll();
  }
  public Account getAccountById(Long id) {
    return accountRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
  }

  // @Transactional
  // public AccountResponse createAccount(AccountRequest request) {
  // //   String type,
  //   // Double balance,
  //   // User user,
  //   // List<Transaction> outgoingTransactions,
  //   // List<Transaction> incomingTransactions
    
  //   if(!userRepository.existsById(request.userId())) {
  //     throw new IllegalArgumentException("User does not exist");
  //   }
  //   Account account = new Account(request.type(), request.balance());
  //   Account accountSaved = accountRepository.save(account);
  //   return AccountResponse.from(accountSaved);
  // }


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
