package com.beezon.couriers_tracking.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.time.Instant
import kotlin.random.Random

@Service
class UpdateScheduler(private val deliveryService: DeliveryService) {
    val log: Logger = LoggerFactory.getLogger(UpdateScheduler::class.java)
    var prevRun: Long = Instant.now().epochSecond

    // TODO thread pool executor
    @Scheduled(fixedRate = 60000)
    fun yourScheduledMethod() {
        waiting()
        val currTime = Instant.now().epochSecond
        log.info("Run scheduler diff time: {}", (currTime - prevRun))
        deliveryService.updateDeliveryState()
        prevRun = Instant.now().epochSecond
    }

    private fun waiting() {
        val randomDelay: Int = Random.nextInt(31) + 30
        try {
            Thread.sleep((randomDelay * 1000).toLong())
        } catch (e: InterruptedException) {
            Thread.currentThread().interrupt()
        }
    }
}