package com.demo.springsec_todo_app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TodoResponse {
    private Integer id;
    private String title;
    private boolean isCompleted;

}
