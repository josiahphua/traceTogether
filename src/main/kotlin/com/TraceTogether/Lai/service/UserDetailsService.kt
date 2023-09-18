package com.TraceTogether.Lai.service

import com.TraceTogether.Lai.dto.UserSecurity
import com.TraceTogether.Lai.repository.UserRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import java.util.Collections


@Service
class UserDetailsService(
        private val userRepository: UserRepository
) : UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        // A method in the repository has to find a user by username
        val user = userRepository.findByUserEmail(username) ?: throw UsernameNotFoundException("$username not found")
        return UserSecurity(
                user.id,
                user.userEmail,
                user.password,
                user.name,
                Collections.singleton(SimpleGrantedAuthority("user"))
        )
    }
}