package com.beezon.storage_manager.service

import com.beezon.storage_manager.dto.request.OrderRequest
import com.beezon.storage_manager.entity.Order
import com.beezon.storage_manager.repository.OrderRepository
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
class OrderServiceImpl(private val orderRepository: OrderRepository): OrderService {
    override fun create(request: OrderRequest): Order {
        val order = Order(
            id = request.orderNum,
            client = request.client,
            itemsCount = request.itemsCount,
            cellId = request.cellId,
        )

        return orderRepository.save(order)
    }

    override fun getOrCreate(request: OrderRequest): Order {
        orderRepository.findById(request.orderNum).getOrNull()?.let {
            return it
        }

        return create(request)
    }

    override fun get(id: String): Order {
        return orderRepository.findById(id)
            .orElseThrow()
    }

    override fun delete(id: String) {
        orderRepository.deleteById(id)
    }


}