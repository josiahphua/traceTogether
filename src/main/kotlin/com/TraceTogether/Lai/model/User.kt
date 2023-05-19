package com.TraceTogether.Lai.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.util.UUID

@Entity
@Table(name = "user")
data class User (
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID = UUID.randomUUID(),

    @Column(name = "username")
    var username: String,

    @Column(name = "password")
    var passwordHash: String,

    @Column(name = "authorities")
    var authorities: MutableCollection<GrantedAuthority> = mutableListOf()
) {
    fun setPassword(password: String) {
        passwordHash = BCryptPasswordEncoder().encode(password)
    }
}