package com.beezon.couriers_tracking.repository

import com.beezon.couriers_tracking.entity.DeliveryEntity
import com.beezon.couriers_tracking.enums.DeliveryStatus
import org.springframework.data.repository.CrudRepository

interface DeliveryRepository: CrudRepository<DeliveryEntity, String> {
    fun findAllByStatusIn(statuses: List<DeliveryStatus>): List<DeliveryEntity>
}