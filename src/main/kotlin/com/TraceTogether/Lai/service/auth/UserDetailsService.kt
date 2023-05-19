package com.TraceTogether.Lai.service.auth

import com.TraceTogether.Lai.auth.UserSecurity
import com.TraceTogether.Lai.repository.auth.UserRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.util.Collections

@Service
class UserDetailsService(
    private val repository: UserRepository
): UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        val user = repository.findByUsername(username) ?: throw Exception("User not found")
        return UserSecurity(
            user.id,
            user.username,
            user.passwordHash,
            Collections.singleton(SimpleGrantedAuthority("USER"))
        )
    }
}