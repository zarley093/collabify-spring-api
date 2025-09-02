package com.collabify.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.collabify.app.model.Account;
import com.collabify.app.service.AccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/accounts")
public class AccountController {

  @Autowired
  private AccountService accountService;

  @GetMapping("/")
  public List<Account> getMethodName(@RequestParam String param) {
      return accountService.listAccounts();
  }
  
  
}
