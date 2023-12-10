package com.beezon.couriers_tracking.service.security

import com.beezon.couriers_tracking.entity.User
import io.jsonwebtoken.JwtParser
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.*
import java.util.concurrent.TimeUnit


@Service
class SecurityService(
    @Value("\${jwt.secret}")
    private val jwtSecret: String,
    @Value("\${jwt.expirationInMillis}")
    private val expirationInMillis: Long,

//    private val jwtParser: JwtParser,
) {

    private val tokenHeader = "Authorization"
    private val tokenPrefix = "Bearer "

    fun createToken(user: User): String {
        val tokenCreateTime = Date()
        val tokenValidity = Date(tokenCreateTime.time + TimeUnit.MINUTES.toMillis(expirationInMillis))
        return Jwts.builder()
            .setExpiration(tokenValidity)
            .signWith(SignatureAlgorithm.HS256, jwtSecret)
            .compact()
    }

    fun resolveToken(request: HttpServletRequest): String? {
        val bearerToken: String = request.getHeader(tokenHeader)
        return if (bearerToken.startsWith(tokenPrefix)) {
            bearerToken.substring(tokenPrefix.length)
        } else null
    }
}