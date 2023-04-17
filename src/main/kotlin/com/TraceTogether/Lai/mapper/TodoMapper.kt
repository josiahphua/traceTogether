package com.TraceTogether.Lai.mapper

import com.TraceTogether.Lai.dto.TodoDto
import com.TraceTogether.Lai.model.ToDo
import org.springframework.stereotype.Component

@Component
class TodoMapper {
  fun toDto(todo: ToDo): TodoDto {
    return TodoDto(
        todo.id,
        todo.title,
        todo.status,
        todo.createdAt,
        todo.todoStartDate,
        todo.todoEndDate,
        todo.description,
        todo.email,
        todo.createdBy,
        todo.priority
    )
  }

  fun toEntity(todoDto: TodoDto): ToDo {
    return ToDo(
      todoDto.id,
      todoDto.title,
      todoDto.status,
      todoDto.createdAt,
      todoDto.todoStartDate,
      todoDto.todoEndDate,
      todoDto.description,
      todoDto.email,
      todoDto.createdBy,
      todoDto.priority
    )
  }
}