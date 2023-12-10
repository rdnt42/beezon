package com.beezon.couriers_tracking.config

import com.beezon.couriers_tracking.entity.User
import com.beezon.couriers_tracking.repository.UserRepository
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component


@Component
class InitTestUser(private val userRepository: UserRepository) {
    @EventListener(ApplicationReadyEvent::class)
    fun handleContextStart() {
        val user = User("admin", "pass")
        userRepository.save(user)
    }
}