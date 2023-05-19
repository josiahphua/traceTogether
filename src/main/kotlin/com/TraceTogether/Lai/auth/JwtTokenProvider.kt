package com.TraceTogether.Lai.auth

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.util.Date

@Component
class JwtTokenProvider(
    @Value("\${jwt.secret}")
    private val jwtSecret: String,
    @Value("\${jwt.expiration}")
    private val validityInMilliseconds: Long,
) {
    private val secretKey = Keys.hmacShaKeyFor(jwtSecret.toByteArray())
    fun generateToken(authentication: Authentication): String {
        val userPrincipal = authentication.principal as UserSecurity
        val now = Date()
        val expiryDate = Date(now.time + validityInMilliseconds)

        return Jwts.builder()
            .setSubject(userPrincipal.username)
            .setIssuedAt(now)
            .setExpiration(expiryDate)
            .signWith(secretKey)
            .compact()
    }

    private fun isTokenExpired(token: String): Boolean {
        val claims = getClaimFromToken(token)
        val expirationDate = claims.expiration
        val now = Date(System.currentTimeMillis())
        return now.before(expirationDate)
    }

    private fun getClaimFromToken(token: String): Claims {
        return Jwts.parserBuilder()
            .setSigningKey(secretKey)
            .build()
            .parseClaimsJws(token)
            .body
    }
}