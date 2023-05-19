package com.TraceTogether.Lai.auth

import com.TraceTogether.Lai.model.User
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.sql.Timestamp
import java.util.Collections
import java.util.Date

class JwtAuthenticationFilter(
    private val jwtTokenProvider: JwtTokenProvider,
    private val authenticationManager: AuthenticationManager
): UsernamePasswordAuthenticationFilter(){
    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {
        val credentials = ObjectMapper().readValue(req.inputStream, User::class.java)
        val auth = UsernamePasswordAuthenticationToken(
            credentials.username,
            credentials.password,
            Collections.singleton(SimpleGrantedAuthority("USER"))
        )
        return authenticationManager.authenticate(auth)
    }

    override fun successfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse,
        chain: FilterChain?,
        authResult: Authentication?
    ) {
        val authResult = authResult ?: throw Exception("Authentication failed")
        val token: String = jwtTokenProvider.generateToken(authResult)
        response.addHeader("Authorization", token)
        response.addHeader("Access-Control-Expose-Headers", "Authorization")
    }

    override fun unsuccessfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse,
        failed: AuthenticationException?
    ) {
        val error = BadCredentialsError()
        response.contentType = "application/json"
        response.status = error.status
        response.writer.append(error.toString())
    }

    private data class BadCredentialsError(
        val timestamp: Long = Date().time,
        val status: Int = 401,
        val message: String = "Username or Password incorrect"
    ) {
        override fun toString(): String {
            return ObjectMapper().writeValueAsString(this)
        }
    }
}