package com.beezon.couriers_tracking.repository

import com.beezon.couriers_tracking.entity.Delivery
import com.beezon.couriers_tracking.enums.DeliveryStatus
import org.springframework.data.repository.CrudRepository

interface DeliveryRepository: CrudRepository<Delivery, String> {
    fun findAllByStatusIn(statuses: List<DeliveryStatus>): List<Delivery>
}