package com.beezon.storage_manager.service

import com.beezon.storage_manager.dto.request.OrderRequest
import com.beezon.storage_manager.entity.Order

interface OrderService {
    fun create(request: OrderRequest): Order
    fun get(id: Long): Order
    fun delete(id: Long)
}