package com.TraceTogether.Lai.repository

import com.TraceTogether.Lai.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface UserRepository : JpaRepository<User, UUID> {
    fun findByUserEmail(email: String?): User?

//    fun findUserById(id: UUID?): User?
}
