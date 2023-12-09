package com.beezon.couriers_tracking.config

import io.jsonwebtoken.JwtParser
import io.jsonwebtoken.Jwts
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

//@Configuration
class JwtParserConfig {
    @Bean
    fun getJwtParser(
        @Value("\${jwt.secret}")
        jwtSecret: String,
    ): JwtParser {
        return Jwts.parser().setSigningKey(jwtSecret)
    }
}