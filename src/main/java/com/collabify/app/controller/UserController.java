package com.collabify.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.collabify.app.dto.UserDto;
import com.collabify.app.model.User;
import com.collabify.app.service.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/api/users")
public class UserController {
  
  @Autowired
  private UserService userService;

  @GetMapping("/")
  public List<UserDto> getUsers() {
      return userService.listUsers();
  }
  

  @GetMapping("/{id}")
  public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
      UserDto user = userService.getUserById(id);
      if (user != null) {
        return ResponseEntity.ok(user);
      } else {
        return ResponseEntity.notFound().build();
      }
  }
  
  @PostMapping("/create")
  public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto data) {
    System.out.print("create wip" + data.username + data.email);
    UserDto created = userService.createUser(data);
    return ResponseEntity.status(201).body(created).ok(data);
  }

  @PutMapping("/update/{id}")
  public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto data) {
    System.out.println("asd" + id + data);
    UserDto updatedUser = userService.updateUser(id,data);
    return ResponseEntity.status(201).body(updatedUser).ok(data);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> delete(@PathVariable Long id) {
    userService.deleteUser(id);
    return new ResponseEntity<>("Successfully Deleted User", HttpStatus.OK);
  }
  
}
