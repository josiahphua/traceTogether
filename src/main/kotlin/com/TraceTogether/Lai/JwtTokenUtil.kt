package com.TraceTogether.Lai

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*


@Component
class JwtTokenUtil {
    @Value("\${jwt.secret}")
    private lateinit var secret: String
    private val expiration = 6000000

    val secretByteArray: ByteArray = secret.toByteArray()
    val now = Date(System.currentTimeMillis())

    fun generateToken(userEmail: String): String {
        val secretKey = Keys.hmacShaKeyFor(secretByteArray)
        return Jwts.builder().setSubject(userEmail).setExpiration(Date(System.currentTimeMillis() + expiration))
                .signWith(secretKey, SignatureAlgorithm.HS512).compact()
    }

    private fun getClaims(token: String) {
        // Jwts.parser().setSigningKey(secretByteArray).parseClaimsJws(token).body
        // older and deprecated method is above. jjwt v0.11.0 or newer use next line
        Jwts.parserBuilder().setSigningKey(secretByteArray).build().parseClaimsJws(token)
    }

    // TODO: Check and see if works, might need to update previous func or getClaims(token).subject
    fun getEmail(token: String) { getClaims(token) }

    fun isTokenValid(token: String): Boolean {
        val claims = getClaims(token)
        val expirationDate = claims.expiration // TODO: check too
        return now.before(expirationDate)
    }
}