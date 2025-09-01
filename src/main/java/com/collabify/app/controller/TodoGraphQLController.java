package com.collabify.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.collabify.app.service.TodoService;
import com.collabify.app.dto.TodoRequest;
import com.collabify.app.dto.TodoResponse;
import com.collabify.app.model.Todo;

import graphql.Assert;
import jakarta.validation.Valid;



@Controller
public class TodoGraphQLController {
  @Autowired
  private TodoService todoService;

  public TodoGraphQLController(TodoService todoService) {
    this.todoService = todoService;
  }

  @QueryMapping
  public List<Todo> todos() {
    return todoService.listTodos();
  }
  
  // @QueryMapping
  // public Todo todo(@Argument("id") Long id) {
  //   Assert.assertNotNull(id, "ID must not be null");
  //   return todoService.getTodoById(id);
  // }

  // @MutationMapping
  // public TodoResponse createTodo(@Valid @Argument("input") TodoRequest input) {
  //   return todoService.createTodo(input);
  // }

  // @MutationMapping
  // public TodoResponse updateTodo(@Argument Long id,  @Argument TodoRequest input) {
  //   Assert.assertNotNull(id, "ID must not be null");
  //   return todoService.updateTodo(id, input);
  // }

  // @MutationMapping
  // public String deleteUser(@Argument Long id) {
  //   Assert.assertNotNull("id", "ID is Required");
  //   return todoService.deleteTodo(id);
  // }

}
