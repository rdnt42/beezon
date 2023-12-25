package com.beezon.storage_manager.service

import com.beezon.storage_manager.dto.request.OrderPartRequest
import com.beezon.storage_manager.entity.OrderPart
import com.beezon.storage_manager.repository.OrderPartRepository
import com.beezon.storage_manager.repository.OrderRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.lang.IllegalArgumentException

@Service
class OrderPartServiceImpl(
    private val orderPartRepository: OrderPartRepository,
    private val orderRepository: OrderRepository,
    ): OrderPartService {

    @Transactional
    override fun create(request: OrderPartRequest): OrderPart {
        val order = orderRepository.findById(request.orderId)
            .orElseThrow()

        if (order.cellId == null) {
            val updateOrder = order.copy(
                cellId = request.cellId
            )
            orderRepository.save(updateOrder)
        } else if (order.cellId != request.cellId) {
            throw IllegalArgumentException("Cell for order part is different then cell for same order")
        }

        val orderPart = OrderPart(
            barcode = request.barcode,
            partNum = request.partNum,
            orderId = order.id,
            dsc = request.dsc,
        )

        return orderPartRepository.save(orderPart)
    }
}