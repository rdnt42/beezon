package com.beezon.couriers_tracking.controller

import com.beezon.couriers_tracking.controller.request.CreateDeliveryRequest
import com.beezon.couriers_tracking.entity.DeliveryEntity
import com.beezon.couriers_tracking.enums.DeliveryStatus
import com.beezon.couriers_tracking.service.DeliveryServiceImpl
import org.springframework.web.bind.annotation.*

@RequestMapping("/api/v1/deliveries")
@RestController
class DeliveryController(private val deliveryInfoService: DeliveryServiceImpl) {

    @GetMapping("/{id}")
    fun getDeliveryInfo(@PathVariable id: String): DeliveryEntity {
        return deliveryInfoService.getDelivery(id)
    }

    @GetMapping
    fun getDeliveryInfo(@RequestParam(value = "statuses", required = false) statuses: List<DeliveryStatus>?): List<DeliveryEntity> {
        return deliveryInfoService.getAllDeliveries(statuses)
    }

    @PostMapping
    fun createDelivery(@RequestBody request: CreateDeliveryRequest): DeliveryEntity {
        return deliveryInfoService.createDelivery(request.url)
    }
}