package com.beezon.couriers_tracking

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@SpringBootApplication
class CouriersTrackingApplication

fun main(args: Array<String>) {
    runApplication<CouriersTrackingApplication>(*args)
}
