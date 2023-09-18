package com.TraceTogether.Lai.repository

import com.TraceTogether.Lai.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findByUserEmail(email: String?): User?
}
