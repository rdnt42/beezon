package com.beezon.couriers_tracking.controller

import com.beezon.couriers_tracking.controller.request.LoginRequest
import com.beezon.couriers_tracking.controller.response.LoginResponse
import com.beezon.couriers_tracking.service.security.JwtTokenService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping(("/login"))
@RestController
class AuthController(
    private val authenticationManager: AuthenticationManager,
    private val jwtTokenService: JwtTokenService
) {
    @PostMapping
    fun login(@RequestBody request: LoginRequest): LoginResponse {
        val authentication: Authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                request.username,
                request.password
            )
        )
        val username = authentication.name
        val token: String = jwtTokenService.generateToken(username)
        return LoginResponse(username, token)
    }
}