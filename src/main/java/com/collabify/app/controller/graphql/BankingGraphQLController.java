package com.collabify.app.controller.graphql;

import java.util.List;
import java.util.stream.Stream;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import com.collabify.app.model.Account;
import com.collabify.app.model.Transaction;
import com.collabify.app.model.User;
import com.collabify.app.dto.account.AccountRequest;
import com.collabify.app.dto.account.AccountResponse;
import com.collabify.app.dto.transaction.TransactionResponse;
import com.collabify.app.service.AccountService;
import com.collabify.app.service.TransactionService;

@Controller
public class BankingGraphQLController {
  @Autowired AccountService accountService;
  @Autowired TransactionService transactionService;

  // Resolver for Account.transactions
  @SchemaMapping(typeName = "Account", field = "transactions")
  public List<Transaction> transactions(Account account) {
    return Stream.concat(
        account.getOutgoingTransactions().stream(),
        account.getIncomingTransactions().stream())
      .toList();
  }

  // Resolver for Account.user because entity exposes getAccountUser()
  @SchemaMapping(typeName = "Account", field = "user")
  public User user(Account account) {
    return account.getAccountUser();
  }

  // Coerce Transaction.timestamp Instant -> String to match schema
  @SchemaMapping(typeName = "Transaction", field = "timestamp")
  public String timestamp(Transaction transaction) {
    Instant ts = transaction.getTimestamp();
    return ts != null ? ts.toString() : null;
  }

  @QueryMapping
  public Account accountById(@Argument Long id) {
    return accountService.getAccountById(id);
  }

  @QueryMapping
  public List<Transaction> transactionsByAccount(@Argument Long accountId) {
    Account account = accountService.getAccountById(accountId);
    return Stream.concat(
        account.getOutgoingTransactions().stream(),
        account.getIncomingTransactions().stream())
      .toList();
  }

  // Mutations
  @MutationMapping
  public Account openAccount(@Argument Long userId, @Argument String type, @Argument Double balance) {
    AccountRequest req = new AccountRequest(type, balance, userId, null, null);
    AccountResponse created = accountService.createAccount(userId, req);
    return accountService.getAccountById(created.id());
  }

  @MutationMapping
  public Account updateAccountType(@Argument Long accountId, @Argument String type) {
    AccountRequest req = new AccountRequest(type, null, null, null, null);
    AccountResponse updated = accountService.updateAccountType(accountId, req);
    return accountService.getAccountById(updated.id());
  }

  @MutationMapping
  public Transaction transferFunds(@Argument Long fromAccountId, @Argument Long toAccountId, @Argument Double amount) {
    TransactionResponse created = accountService.transfer(fromAccountId, toAccountId, amount);
    return transactionService.showTransaction(created.id());
  }

  @MutationMapping
  public Account deposit(@Argument Long accountId, @Argument Double amount) {
    AccountResponse updated = accountService.deposit(accountId, amount);
    return accountService.getAccountById(updated.id());
  }
  @MutationMapping
  public Account withdraw(@Argument Long accountId, @Argument Double amount) {
    AccountResponse updated = accountService.withdraw(accountId, amount);
    return accountService.getAccountById(updated.id());
  }
}
