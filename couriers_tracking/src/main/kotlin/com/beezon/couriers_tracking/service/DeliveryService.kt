package com.beezon.couriers_tracking.service

import com.beezon.couriers_tracking.entity.DeliveryEntity
import com.beezon.couriers_tracking.enums.DeliveryStatus

interface DeliveryService {
    fun createDelivery(deliveryUrl: String): DeliveryEntity
    fun getDelivery(id: String): DeliveryEntity
    fun updateDeliveryState()
    fun getAllDeliveries(statuses: List<DeliveryStatus>?): List<DeliveryEntity>
}