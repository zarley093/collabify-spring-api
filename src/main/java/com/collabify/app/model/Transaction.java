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
  @JoinColumn(name = "from_account_id")
  private Account fromAccount;

  @ManyToOne
  @JoinColumn(name = "to_account_id")
  private Account toAccount;

  public Long getId() {
    return id;
  }

  public void setType(String type) {
   this.type = type;
  }
  public String getType() {
    return type;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }
  public Double getAmount() {
    return amount;
  }

  public void setFromAccount(Account fromAccount) {
    this.fromAccount = fromAccount;
  }
  public Account getFromAccount() {
    return fromAccount;
  }

  public void setToAccount(Account toAccount) {
    this.toAccount = toAccount;
  }
  public Account getToAccount() {
    return toAccount;
  }

  public void setTimestamp(Instant timestamp) {
    this.timestamp = timestamp;
  }
  public Instant getTimestamp() {
    return timestamp;
  }

} 