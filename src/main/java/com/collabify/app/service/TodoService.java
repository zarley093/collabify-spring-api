package com.collabify.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.collabify.app.dto.todo.TodoRequest;
import com.collabify.app.dto.todo.TodoResponse;
import com.collabify.app.model.Todo;
import com.collabify.app.repository.TodoRepository;

import jakarta.transaction.Transactional;

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

  @Transactional
  public TodoResponse createTodo(TodoRequest request) {
    Todo todo = new Todo(request.title(), request.description(), request.completed());
    
    Todo todoSaved = todoRepository.save(todo);
    return TodoResponse.from(todoSaved);
  }

  public Todo getTodoById(Long id) {
    return todoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Data not found!"));
  }

  @Transactional
  public TodoResponse updateTodo(Long id, TodoRequest request) {
    Todo todo = todoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Data not found!"));
    
    todo.setTitle(request.title());
    todo.setDescription(request.description());
    todo.setCompleted(request.completed());

    return TodoResponse.from(todo);
  }

  public String deleteTodo(Long id) {
    if (!todoRepository.existsById(id)) {
      throw new IllegalArgumentException("Todo not found");
    }

    todoRepository.deleteById(id);

    String response = null;

    if (!todoRepository.existsById(id)) {
      response = "Successfully Deleted Todo";
      return response;
    } else {
      response = "Something went Wrong!";
      return response;
    }
  }
}
