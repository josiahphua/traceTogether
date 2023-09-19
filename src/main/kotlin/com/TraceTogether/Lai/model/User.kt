package com.TraceTogether.Lai.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "users")
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        val id: UUID,

        @Column(unique = true, nullable = false)
        val userEmail: String,

        @Column(nullable = false)
        var password: String,

        @Column
        val name: String
)
