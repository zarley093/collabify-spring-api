package com.collabify.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.collabify.app.dto.UserDto;
import com.collabify.app.model.User;
import com.collabify.app.service.UserService;

import graphql.Assert;
import jakarta.validation.Valid;

@Controller
public class UserGraphQLController {
  @Autowired 
  private UserService userService;
  // Explicitly bind to the schema field name: `user`

  
  @QueryMapping
  public User user(@Argument("id") Long id) {
    Assert.assertNotNull(id, "id must not be null");
    return userService.getUserById(id);
  }

  // Explicitly bind to the schema field name: `users`
  @QueryMapping
  public List<User> users() {
    return userService.listUsers();
  }

  @MutationMapping
  public User createUser(@Valid @Argument("input") User input ) {
    System.out.print("asd " + input);
    User user = new User();
    user.setUsername(input.getUsername());
    user.setEmail(input.getEmail());
    return userService.createUser(input);
  }

  @MutationMapping
  public User updateUser(@Valid @Argument Long id, @Argument("input") User input) {
    System.out.print("update ");
    Assert.assertNotNull(id, "ID is required");
    User userToUpdate = userService.getUserById(id);
    if(userToUpdate == null) {
      throw new IllegalArgumentException("User not found with id: " + id);
    }
    if (input.getUsername() != null) userToUpdate.setUsername(input.username);
    if (input.getEmail() != null) userToUpdate.setEmail(input.email);
    return userService.updateUser(id, userToUpdate);
  }

  @MutationMapping
  public Boolean deleteUser(@Argument Long id) {
    Assert.assertNotNull(id, "ID is required");
    return userService.deleteUser(id);
  }
}