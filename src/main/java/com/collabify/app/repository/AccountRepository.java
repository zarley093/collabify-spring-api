package com.collabify.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.collabify.app.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

  
} 
