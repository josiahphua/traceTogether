package com.TraceTogether.Lai.auth

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.UUID

class UserSecurity(
    val id: UUID,
    val username: String,
    private val uPassword: String,
    private val uAuthority: MutableCollection<GrantedAuthority>
): UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return uAuthority
    }

    override fun getPassword(): String {
        return uPassword
    }

    override fun getUsername(): String {
        return username
    }

    override fun isAccountNonExpired(): Boolean {
        return true;
    }

    override fun isAccountNonLocked(): Boolean {
        return true;
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true;
    }

    override fun isEnabled(): Boolean {
        return true;
    }
}