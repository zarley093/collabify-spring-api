package com.collabify.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.collabify.app.dto.UserDto;
import com.collabify.app.model.User;
// import graphql.com.google.common.base.Optional;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByEmail(String email);
}