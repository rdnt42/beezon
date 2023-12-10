package com.beezon.couriers_tracking.service.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.security.Key
import java.util.*


@Service
class JwtTokenService(
    @Value("\${jwt.secret}")
    private val jwtSecret: String,
    @Value("\${jwt.expirationInMillis}")
    private val expirationInMillis: Long,

    ) {
    fun generateToken(username: String): String {
        return Jwts
            .builder()
            .setSubject(username)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + expirationInMillis))
            .signWith(getSignInKey(), SignatureAlgorithm.HS256)
            .compact();
    }

    fun isTokenValid(token: String, userDetails: UserDetails): Boolean {
        val username: String = extractUsername(token)
        return username == userDetails.username && !isTokenExpired(token)
    }

    private fun isTokenExpired(token: String): Boolean {
        val claims = extractAllClaims(token)

        return claims.expiration.before(Date())
    }

    fun extractUsername(token: String): String {
        val claims = extractAllClaims(token)
        return claims.subject
    }

    private fun extractAllClaims(token: String): Claims {
        return Jwts
            .parserBuilder()
            .setSigningKey(getSignInKey())
            .build()
            .parseClaimsJws(token)
            .body;
    }

    private fun getSignInKey(): Key {
        val keyBytes = Decoders.BASE64.decode(jwtSecret)

        return Keys.hmacShaKeyFor(keyBytes)
    }
}