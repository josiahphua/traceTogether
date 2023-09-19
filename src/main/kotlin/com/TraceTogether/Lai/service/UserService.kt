package com.TraceTogether.Lai.service

import com.TraceTogether.Lai.model.User
import com.TraceTogether.Lai.repository.UserRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UserService(
        private val userRepository: UserRepository,
        private val passwordEncoder: BCryptPasswordEncoder
) {
    fun saveUser(user: User): User {
        user.password = passwordEncoder.encode(user.password)
        return userRepository.save(user)
    }

    fun findByEmail(userEmail: String): User? {
        return userRepository.findByUserEmail(userEmail)
    }

    fun findById(userId: UUID): User? {
        return userRepository.findById(userId).orElse(null)
    }

}