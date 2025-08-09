package com.demo.springsec_todo_app.repository;

import com.demo.springsec_todo_app.models.Todo;
import com.demo.springsec_todo_app.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo,Integer> {
    List<Todo> findByUser(User user);
}
