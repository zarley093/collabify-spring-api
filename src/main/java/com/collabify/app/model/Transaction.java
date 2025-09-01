package com.collabify.app.model;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;


@Entity
public class Transaction {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Double amount;
  private String type; // e.g., "TRANSFER", "DEPOSIT", "WITHDRAWAL"
  private Instant timestamp;
  @ManyToOne
  @JoinColumn(name = "fromAccountId")
  private Account fromAccount;
  @ManyToOne
  @JoinColumn(name = "toAccountId")
  private Account toAccount;

} 