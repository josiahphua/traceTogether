package com.TraceTogether.Lai.api

import com.TraceTogether.Lai.dto.TodoDto
import com.TraceTogether.Lai.service.TodoService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/todos")
class TodoController(
    val todoService: TodoService
) {
  @GetMapping
  fun getAllTodos(): List<TodoDto> {
    return todoService.getAllTodos()
  }

  @PostMapping
  fun addTodo(@RequestBody todoDto: TodoDto): TodoDto {
    return todoService.createTodo(todoDto)
  }

  @GetMapping("/{id}")
  fun getTodoById(@PathVariable id: Long): TodoDto {
    return todoService.getTodoByID(id)
  }

  @PutMapping("/{id}")
  fun updateTodo(
    @PathVariable id: Long,
    @RequestBody todoDto: TodoDto
  ): TodoDto {
    return todoService.updateTodo(id, todoDto)
  }

  @DeleteMapping("/{id}")
  fun deleteTodo(@PathVariable id: Long) {
    return todoService.deleteTodo(id)
  }
}