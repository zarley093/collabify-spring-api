package com.collabify.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.collabify.app.dto.UserDto;
import com.collabify.app.model.User;
import com.collabify.app.repository.UserRepository;

// import graphql.com.google.common.base.Optional;
import java.util.Optional;
@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;

  public List<User> listUsers() {
    return userRepository.findAll();
  }

  public User createUser(User user) {
    return userRepository.save(user);
  }

  public User getUserById(Long id) {
    return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Data not found!"));
  }

  public User updateUser(Long id, User data) {
    User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Data not found!"));
    System.out.print("updateings" + user);
    user.username = data.username;
    user.email = data.email;

    User updatedUser = userRepository.save(user);
    System.out.print("updated: " + updatedUser);
    return updatedUser;
  }

  public Boolean deleteUser(Long id) {
    if(!userRepository.existsById(id)) {
      throw new IllegalArgumentException("User not found with id: " + id);
    }
    userRepository.deleteById(id);

    if(!userRepository.existsById(id)) {
      System.out.println("deleted");
      return true;
    } else {
      return false;
    }
  }

  // public UserDto getUserByIdDTO (Long id) {
  //   return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Data not found! (DTO)"));
  // }



  public Optional<User> findByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  // private UserDto convertToDTO(User user) {
  //       // logic to convert User to UserDTO
  // }
}
