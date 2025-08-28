package com.collabify.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.collabify.app.dto.UserDto;
import com.collabify.app.model.User;
import com.collabify.app.repository.UserRepository;

import graphql.com.google.common.base.Optional;

@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;

  public List<UserDto> listUsers() {
    return userRepository.findAll();
  }

  public UserDto createUser(UserDto user) {
    return userRepository.save(user);
  }

  public UserDto getUserById(Long id) {
    return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Data not found!"));
  }

  public UserDto updateUser(Long id, UserDto data) {
    UserDto user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Data not found!"));
    System.out.println("updateings");
    user.username = data.username;
    user.email = data.email;
    return userRepository.save(user);
  }

  public void deleteUser(Long id) {
    userRepository.deleteById(id);
  }

  // public UserDto getUserByIdDTO (Long id) {
  //   return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Data not found! (DTO)"));
  // }



  public Optional<UserDto> findByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  // private UserDto convertToDTO(User user) {
  //       // logic to convert User to UserDTO
  // }
}
