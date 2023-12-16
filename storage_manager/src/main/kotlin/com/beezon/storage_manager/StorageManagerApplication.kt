package com.beezon.storage_manager

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@SpringBootApplication
class StorageManagerApplication

fun main(args: Array<String>) {
    runApplication<StorageManagerApplication>(*args)
}
