package com.collabify.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.collabify.app.dto.UserDto;
import com.collabify.app.model.User;
import com.collabify.app.service.UserService;

import graphql.Assert;

@Controller
public class UserGraphQLController {
  @Autowired 
  private UserService userService;

  // Explicitly bind to the schema field name: `user`
  @QueryMapping
  public UserDto user(@Argument("id") Long id) {
    Assert.assertNotNull(id, "id must not be null");
    return userService.getUserById(id);
  }

  // Explicitly bind to the schema field name: `users`
  @QueryMapping
  public List<UserDto> users() {
    return userService.listUsers();
  }
}
