package com.beezon.couriers_tracking.controller

import com.beezon.couriers_tracking.controller.request.CreateDeliveryRequest
import com.beezon.couriers_tracking.entity.Delivery
import com.beezon.couriers_tracking.enums.DeliveryStatus
import com.beezon.couriers_tracking.service.DeliveryServiceImpl
import org.springframework.web.bind.annotation.*

@RequestMapping("/api/v1/deliveries")
@RestController
class DeliveryController(private val deliveryInfoService: DeliveryServiceImpl) {

    @GetMapping("/{id}")
    fun getDeliveryInfo(@PathVariable id: String): Delivery {
        return deliveryInfoService.getDelivery(id)
    }

    @GetMapping
    fun getDeliveryInfo(@RequestParam(value = "statuses", required = false) statuses: List<DeliveryStatus>?): List<Delivery> {
        return deliveryInfoService.getAllDeliveries(statuses)
    }

    @PostMapping
    fun createDelivery(@RequestBody request: CreateDeliveryRequest): Delivery {
        return deliveryInfoService.createDelivery(request)
    }
}