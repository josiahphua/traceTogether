package com.TraceTogether.Lai.api

import com.TraceTogether.Lai.model.User
import com.TraceTogether.Lai.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/users")
class UserController (
        private val userService: UserService
) {
    @PostMapping("/create")
    fun createUser(@RequestBody user: User): ResponseEntity<User> {
        val savedUser = userService.saveUser(user)
        return ResponseEntity(savedUser, HttpStatus.CREATED)
    }

    @GetMapping("/email")
    fun getUserByEmail(@RequestBody user: User): ResponseEntity<User> {
        val user = userService.findByEmail(user.userEmail)
        return user?.let {
            ResponseEntity(it, HttpStatus.OK)
        } ?: ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: UUID): ResponseEntity<User> {
        val user = userService.findById(id)
        return user?.let {
            ResponseEntity(it, HttpStatus.OK)
        } ?: ResponseEntity(HttpStatus.NOT_FOUND)
    }
}