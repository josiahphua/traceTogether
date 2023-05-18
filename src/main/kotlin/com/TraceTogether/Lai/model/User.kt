package com.TraceTogether.Lai.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
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
    var password: String,
) {
    fun setPassword(pass: String) {
        password = pass
    }
}