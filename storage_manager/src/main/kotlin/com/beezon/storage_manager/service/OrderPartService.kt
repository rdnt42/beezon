package com.beezon.storage_manager.service

import com.beezon.storage_manager.dto.request.OrderPartRequest
import com.beezon.storage_manager.entity.OrderPart

interface OrderPartService {
    fun create(request: OrderPartRequest): OrderPart
}