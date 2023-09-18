package com.TraceTogether.Lai.auth.filters

import com.TraceTogether.Lai.JwtTokenUtil
import com.TraceTogether.Lai.dto.LoginDto
import com.TraceTogether.Lai.dto.UserSecurity
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.util.Collections

class JwtAuthenticationFilter(
        private val jwtTokenUtil: JwtTokenUtil,
        private val authenticationManager: AuthenticationManager
) : UsernamePasswordAuthenticationFilter() {

    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication {
        val credentials = ObjectMapper().readValue(request?.inputStream, LoginDto::class.java)
        val auth = UsernamePasswordAuthenticationToken(
                credentials.email,
                credentials.password,
                Collections.singleton(SimpleGrantedAuthority("user"))
        )
        return authenticationManager.authenticate(auth)
    }

    override fun successfulAuthentication(request: HttpServletRequest?, response: HttpServletResponse?, chain: FilterChain?, authResult: Authentication?) {
        val username = (authResult?.principal as UserSecurity).username
        val token: String = jwtTokenUtil.generateToken(username)
        response?.addHeader("Authorization", token)
        response?.addHeader("Access-Control-Expose-Headers", "Authorization")
    }

    override fun unsuccessfulAuthentication(request: HttpServletRequest?, response: HttpServletResponse?, failed: AuthenticationException?) {
        val error = BadCredentialsException("Something wrong with your username or password?")
        response?.status = HttpStatus.UNAUTHORIZED.value()
        response?.contentType = "application/json"
        response?.writer?.append(error.toString()) // TODO: check if I need to do this, or should write in try catch block?
    }
}

