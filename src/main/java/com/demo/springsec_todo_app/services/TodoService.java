package com.demo.springsec_todo_app.services;

import com.demo.springsec_todo_app.dto.TodoRequest;
import com.demo.springsec_todo_app.dto.TodoResponse;
import com.demo.springsec_todo_app.models.Todo;
import com.demo.springsec_todo_app.repository.TodoRepository;
import com.demo.springsec_todo_app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final UserRepository userRepository;
    private final TodoRepository todoRepository;

    public TodoResponse addTodo(String email, TodoRequest todoRequest) {
        var user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Todo todo = Todo.builder()
                .title(todoRequest.getTitle())
                .isCompleted(todoRequest.isCompleted())
                .user(user)
                .build();

        var savedTodo = todoRepository.save(todo);
        return TodoResponse.builder()
                .id(savedTodo.getId())
                .title(savedTodo.getTitle())
                .isCompleted(savedTodo.isCompleted())
                .build();
    }

    public List<TodoResponse> getAllTodos(String email) {
        var user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return todoRepository.findByUser(user)
                .stream()
                .map(todo ->
                        TodoResponse.builder()
                                .id(todo.getId())
                                .title(todo.getTitle())
                                .isCompleted(todo.isCompleted())
                                .build()
                ).toList();
    }
}
