package com.demo.springsec_todo_app.controllers;

import com.demo.springsec_todo_app.dto.TodoRequest;
import com.demo.springsec_todo_app.dto.TodoResponse;
import com.demo.springsec_todo_app.services.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/todos")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<TodoResponse> addTodo(Authentication authentication, @RequestBody TodoRequest todoRequest) {
        String userEmail = authentication.getName();
        return ResponseEntity.ok(todoService.addTodo(userEmail, todoRequest));
    }

    @GetMapping
    public ResponseEntity<List<TodoResponse>> getAllTodos(Authentication authentication) {
        String userEmail = authentication.getName();
        return ResponseEntity.ok(todoService.getAllTodos(userEmail));
    }

}
