package com.TraceTogether.Lai.service

import com.TraceTogether.Lai.dto.TodoDto
import com.TraceTogether.Lai.mapper.TodoMapper
import com.TraceTogether.Lai.repository.TodoRepository
import org.springframework.stereotype.Service

@Service
class TodoService(
    val todoRepository: TodoRepository,
    val todoMapper: TodoMapper
) {
  fun getAllTodos(): List<TodoDto> {
    val todos = todoRepository.findAll()
    return todos.map { todoMapper.toDto(it) }
  }

  fun getTodoByID(id: Long): TodoDto {
    val todo = todoRepository.findById(id).orElseThrow()
    return todoMapper.toDto(todo)
  }

  fun createTodo(todoDto: TodoDto): TodoDto{
    val todo = todoMapper.toEntity(todoDto)
    val savedTodo = todoRepository.save(todo)
    return todoMapper.toDto(savedTodo)
  }

  fun updateTodo(id: Long, todoDto: TodoDto): TodoDto {
    val currentTodo = todoRepository.findById(id).orElseThrow()
    currentTodo.title = todoDto.title
    currentTodo.status = todoDto.status
    currentTodo.createdAt = todoDto.createdAt
    currentTodo.todoStartDate = todoDto.todoStartDate
    currentTodo.todoEndDate = todoDto.todoEndDate
    currentTodo.description = todoDto.description
    currentTodo.email = todoDto.email
    currentTodo.createdBy = todoDto.createdBy
    currentTodo.priority = todoDto.priority
    val savedTodo = todoRepository.save(currentTodo)
    return todoMapper.toDto(savedTodo)
  }

  fun deleteTodo(id: Long) {
    todoRepository.deleteById(id)
  }
}