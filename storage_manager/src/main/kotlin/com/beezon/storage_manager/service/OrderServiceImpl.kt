package com.beezon.storage_manager.service

import com.beezon.storage_manager.dto.request.OrderRequest
import com.beezon.storage_manager.entity.Order
import com.beezon.storage_manager.repository.OrderRepository
import org.springframework.stereotype.Service

@Service
class OrderServiceImpl(private val orderRepository: OrderRepository): OrderService {
    override fun create(request: OrderRequest): Order {
        val order = Order(
            barCode = request.barCode,
            client = request.client,
            itemsCount = request.itemsCount,
            cellId = request.cellId,
        )

        return orderRepository.save(order)
    }

    override fun get(id: Long): Order {
        return orderRepository.findById(id)
            .orElseThrow()
    }

    override fun delete(id: Long) {
        orderRepository.deleteById(id)
    }


}