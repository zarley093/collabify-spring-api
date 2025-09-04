package com.collabify.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.collabify.app.repository.TransactionRepository;

import jakarta.transaction.Transactional;

import com.collabify.app.dto.transaction.TransactionRequest;
import com.collabify.app.model.Transaction;

public class TransactionService {
  @Autowired
  private final TransactionRepository transactionRepository;

  public TransactionService (TransactionRepository transactionRepository) {
    this.transactionRepository = transactionRepository;
  }

  public List <Transaction> listTransactions() {
    return transactionRepository.findAll();
  } 

  public Transaction showTransaction(Long transactionId) {
    return transactionRepository.findById(transactionId).orElseThrow(() -> new IllegalArgumentException());
  }
  @Transactional
  public void deleteTransaction(Long transactionId) {
    if(!transactionRepository.existsById(transactionId)) {
      throw new IllegalArgumentException("Transaction not found with id: " + transactionId);
    }
    transactionRepository.deleteById(transactionId);
  }
  
}