package com.collabify.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.collabify.app.dto.user.UserRequest;
import com.collabify.app.dto.user.UserResponse;
import com.collabify.app.exception.ResourceNotFoundException;
import com.collabify.app.model.User;
import com.collabify.app.repository.UserRepository;

import jakarta.transaction.Transactional;
@Service
public class UserService {
  
  @Autowired
  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public List<User> listUsers() {
    return userRepository.findAll();
  }

  @Transactional
  public UserResponse createUser(UserRequest request) {
    if (userRepository.existsByEmail(request.email())) {
      throw new ResourceNotFoundException("Email already in use");
    }
    User user = new User(request.username(), request.email());
    User saved = userRepository.save(user);
    return UserResponse.from(saved);
  }

  public User getUserById(Long id) {
    return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Data not found!"));
  }

  // public User updateUser(Long id, User data) {
  //   User user = new User();
  //   User userFound = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Data not found!"));
  //   System.out.print("updateings" + user);
  //   userFound.username = userContructor.username;
  //   userFound.email = userContructor.email;

  //   User updatedUser = userRepository.save(user);
  //   System.out.print("updated: " + updatedUser);
  //   return updatedUser;
  // }
  @Transactional
  public UserResponse updateUser(Long id, UserRequest request) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    if (!user.getEmail().equals(request.email()) && userRepository.existsByEmail(request.email())) {
      throw new ResourceNotFoundException("Email already in use");
    }
    user.setUsername(request.username());
    user.setEmail(request.email());
    return UserResponse.from(user); // managed entity, changes flushed at commit
  }


  public Boolean deleteUser(Long id) {
    if(!userRepository.existsById(id)) {
      throw new ResourceNotFoundException("User not found with id: " + id);
    }
    userRepository.deleteById(id);

    if(!userRepository.existsById(id)) {
      System.out.println("deleted");
      return true;
    } else {
      return false;
    }
  }
}
