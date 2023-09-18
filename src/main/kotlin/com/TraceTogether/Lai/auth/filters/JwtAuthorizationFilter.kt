package com.TraceTogether.Lai.auth.filters

import com.TraceTogether.Lai.JwtTokenUtil
import com.TraceTogether.Lai.service.UserDetailsService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders.AUTHORIZATION
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter

class JwtAuthorizationFilter(
        private val jwtTokenUtil: JwtTokenUtil,
        private val service: UserDetailsService,
        authenticationManager: AuthenticationManager
) : BasicAuthenticationFilter(authenticationManager) {
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        val header = request.getHeader(AUTHORIZATION)
        if (header == null || !header.startsWith("Bearer ")) {
            chain.doFilter(request,response)
            return
        }
        getAuthentication(header.substring(7))?.also {
            SecurityContextHolder.getContext().authentication = it
        }
        chain.doFilter(request,response)
    }

    private fun getAuthentication(token: String): UsernamePasswordAuthenticationToken? {
        if (!jwtTokenUtil.isTokenValid(token)) return null
        val email = jwtTokenUtil.getEmail(token)
        val user = service.loadUserByUsername(email.toString())
        return UsernamePasswordAuthenticationToken(user, null, user.authorities)
    }
}