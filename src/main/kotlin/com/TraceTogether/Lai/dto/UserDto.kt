package com.TraceTogether.Lai.dto

import java.util.UUID

data class UserDto(
        val id: UUID? = null,
        val userEmail: String,
        val password: String? = null,
        val name: String
)
