package com.beezon.couriers_tracking.service

import com.beezon.couriers_tracking.controller.request.CreateDeliveryRequest
import com.beezon.couriers_tracking.entity.Delivery
import com.beezon.couriers_tracking.enums.DeliveryStatus

interface DeliveryService {
    fun createDelivery(request: CreateDeliveryRequest): Delivery
    fun getDelivery(id: String): Delivery
    fun updateDeliveryState()
    fun getAllDeliveries(statuses: List<DeliveryStatus>?): List<Delivery>
}