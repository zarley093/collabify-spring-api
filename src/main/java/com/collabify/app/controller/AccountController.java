package com.collabify.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.collabify.app.dto.account.AccountRequest;
import com.collabify.app.dto.account.AccountResponse;
import com.collabify.app.model.Account;
import com.collabify.app.service.AccountService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/accounts")
public class AccountController {

  @Autowired
  private AccountService accountService;

  @GetMapping("/")
  public List<Account> getAccounts() {
    return accountService.listAccounts();
  }

  @PostMapping("/create/{userId}")
  public ResponseEntity<AccountResponse> createAccount(@PathVariable Long userId, @Valid @RequestBody AccountRequest data) {
    AccountResponse accountCreated = accountService.createAccount(userId, data);
    return ResponseEntity.status(201).body(accountCreated);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Account> showAccount(@PathVariable Long id) {
    Account account = accountService.getAccountById(id);
    if (account != null) { 
      return ResponseEntity.ok(account);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping("/update-type/{accountId}")
  public ResponseEntity<Account>  updateAccountType(@PathVariable Long accountId, @Valid @RequestBody AccountRequest data) {
    Account account = accountService.getAccountById(accountId);
    if (account != null) { 
      return ResponseEntity.ok(account);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/delete-account/{accountId}") 
  public ResponseEntity<Void> delete(@PathVariable Long accountId) {
    accountService.deleteAccount(accountId);
    return null;
  }
}
