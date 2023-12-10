package com.beezon.couriers_tracking.config

import com.beezon.couriers_tracking.entity.User
import com.beezon.couriers_tracking.repository.UserRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component


@Component
class InitTestUser(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    @Value("\${manager.username}")
    private val username: String,
    @Value("\${manager.password}")
    private val password: String,
) {
    @EventListener(ApplicationReadyEvent::class)
    fun handleContextStart() {
        val encode = passwordEncoder.encode(password)
        val user = User(username, encode)
        userRepository.save(user)
    }
}