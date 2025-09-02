package com.collabify.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.collabify.app.model.Account;
public interface AccountRepository extends JpaRepository<Account, Long> {
  default boolean checkValidAccountType(String type) {
    switch (type) {
      case "savings":
        return true;
      case "checking":
        return true;
      default:
        return false;
    }
  }
  
} 
