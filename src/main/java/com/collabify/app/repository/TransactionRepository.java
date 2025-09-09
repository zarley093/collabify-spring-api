package com.collabify.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.collabify.app.model.Account;
import com.collabify.app.model.Transaction;
import com.collabify.app.dto.transaction.TransactionDto;
import java.util.List;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
  public static final String FIND_ACCOUNT = "SELECT id, amount, type, timestamp, FROM account";

  // @Query(value = FIND_ACCOUNT, nativeQuery = true)
  List<Transaction> findByFromAccount(Account fromAccount);

}
