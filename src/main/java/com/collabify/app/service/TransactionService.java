package com.collabify.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.collabify.app.repository.TransactionRepository;

import jakarta.transaction.Transactional;

import com.collabify.app.model.Account;
import com.collabify.app.model.Transaction;

@Service
public class TransactionService {
  private final TransactionRepository transactionRepository;

  public TransactionService(TransactionRepository transactionRepository) {
    this.transactionRepository = transactionRepository;
  }

  public List<Transaction> listTransactions() {
    return transactionRepository.findAll();
  }

  public List<Transaction> listAccountTransactions(Account account) {
    return transactionRepository.findByFromAccount(account);
  }

  public Transaction showTransaction(Long transactionId) {
    return transactionRepository.findById(transactionId).orElseThrow(() -> new IllegalArgumentException());
  }

  @Transactional
  public void deleteTransaction(Long transactionId) {
    if (!transactionRepository.existsById(transactionId)) {
      throw new IllegalArgumentException("Transaction not found with id: " + transactionId);
    }
    transactionRepository.deleteById(transactionId);
  }
}
