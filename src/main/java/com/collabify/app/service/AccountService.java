package com.collabify.app.service;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.collabify.app.dto.account.AccountRequest;
import com.collabify.app.dto.account.AccountResponse;
import com.collabify.app.dto.transaction.TransactionResponse;
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
    return accountRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Account not found!"));
  }

  @Transactional
  public AccountResponse createAccount(Long userId, AccountRequest request) {
    if(!userRepository.existsById(userId)) {
      throw new ResourceNotFoundException("User does not exist");
    }
    User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Data not found!"));

    Account account = new Account(request.type(), request.balance(), user);
    Account accountSaved = accountRepository.save(account);
    return AccountResponse.from(accountSaved);
  }

  @Transactional
  public AccountResponse updateAccountType(Long id, AccountRequest request) {
    Account account = accountRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Account not found"));
    if (!accountRepository.checkValidAccountType(request.type())) {
      throw new ResourceNotFoundException("Invalid Account Type");
    }
    System.out.println("request" + request.type());
    account.setType(request.type());
    return AccountResponse.from(account);
  }

  @Transactional
  public TransactionResponse transfer(Long fromAccountId, Long toAccountId, Double amount) {
    Account fromAccount = accountRepository.findById(fromAccountId).orElseThrow(() -> new ResourceNotFoundException("From account not found"));
    Account toAccount = accountRepository.findById(toAccountId).orElseThrow(() -> new ResourceNotFoundException("To account not found"));

    if (fromAccount.getBalance() < amount) {
      throw new ResourceNotFoundException("Insufficient funds");
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
    Transaction transactionSaved = transactionRepository.save(transaction);
    return TransactionResponse.from(transactionSaved);
  }

  @Transactional
  public void deleteAccount(Long accountId) {
    if(!accountRepository.existsById(accountId)) {
      throw new ResourceNotFoundException("User not found with ID: " + accountId);
    }
    accountRepository.deleteById(accountId);
  }


}
