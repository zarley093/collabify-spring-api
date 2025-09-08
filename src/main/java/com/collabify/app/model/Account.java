package com.collabify.app.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.util.ArrayList;
import java.util.List;
@Entity
public class Account {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String type; //e.g., "savings", "checking"

  private Double balance;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @OneToMany(mappedBy = "fromAccount", cascade = CascadeType.ALL)
  private List<Transaction> outgoingTransactions = new ArrayList<>();

  @OneToMany(mappedBy = "toAccount", cascade = CascadeType.ALL)
  private List<Transaction> incomingTransactions = new ArrayList<>();

  // JPA requires a no-args constructor
  protected Account() {}

  // Convenience constructor used in services 
  public Account(String type, Double balance, User user) {
    this.type = type;
    this.balance = balance;
    this.user = user;
  }

  // Officially "Public" constructor used for gets/shows 
  public Account(String type, Double balance, User user, List<Transaction> outgoingTransactions, List<Transaction> incomingTransactions) {
    this.type = type;
    this.balance = balance;
    this.user = user;
    this.outgoingTransactions = outgoingTransactions;
    this.incomingTransactions = incomingTransactions;
  }

  public Long getId() {
    return id;
  }

  public String getType() {
    return type;
  }

   public void setType(String newType) {
    this.type = newType;
  }

  public Double getBalance() {
    return balance;
  }
  public void setBalance(Double newBalance) {
    this.balance = newBalance;
  }

  public User getAccountUser() {
    return user;
  }

  public List<Transaction> getOutgoingTransactions() {
    return outgoingTransactions;
  }

  public List<Transaction> getIncomingTransactions() {
    return incomingTransactions;
  }
 

}
