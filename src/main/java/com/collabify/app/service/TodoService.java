package com.collabify.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.collabify.app.model.Todo;
import com.collabify.app.repository.TodoRepository;

@Service
public class TodoService {
  @Autowired
  private TodoRepository todoRepository;

  public TodoService(TodoRepository todoRepository) {
    this.todoRepository = todoRepository;
  }

  public List<Todo> listTodos() {
    return todoRepository.findAll();
  }
}
