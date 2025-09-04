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

  @Transactional
  public AccountResponse createAccount(Long userId, AccountRequest request) {
    if(!userRepository.existsById(userId)) {
      throw new IllegalArgumentException("User does not exist");
    }
    User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Data not found!"));

    Account account = new Account(request.type(), request.balance(), user);
    Account accountSaved = accountRepository.save(account);
    return AccountResponse.from(accountSaved);
  }

  @Transactional
  public AccountResponse updateAccountType(Long id, AccountRequest request) {
    Account account = accountRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Account not found"));
    if (!accountRepository.checkValidAccountType(request.type())) {
      throw new IllegalArgumentException("Invalid Account Type");
    }
    account.setType(request.type());
    return AccountResponse.from(account);
  }

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

    // Record transaction -- the transaction entity, not the transaction sequence
    Transaction transaction = new Transaction();
    transaction.setType("TRANSFER");
    transaction.setAmount(amount);
    transaction.setFromAccount(fromAccount);
    transaction.setToAccount(toAccount);
    transaction.setTimestamp(Instant.now());
    return transactionRepository.save(transaction);
  }

  @Transactional
  public void deleteAccount(Long accountId) {
    if(!accountRepository.existsById(accountId)) {
      throw new IllegalArgumentException("User not found with ID: " + accountId);
    }
    accountRepository.deleteById(accountId);
  }


}
