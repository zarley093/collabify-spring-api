package com.collabify.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.collabify.app.model.Account;
import com.collabify.app.model.Transaction;
import java.util.List;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
  List<Transaction> findByFromAccount(Account fromAccount);

}
