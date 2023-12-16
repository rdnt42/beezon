package com.beezon.storage_manager.controller

import com.beezon.storage_manager.dto.request.OrderRequest
import com.beezon.storage_manager.entity.Order
import com.beezon.storage_manager.service.OrderService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RequestMapping("/api/v1/orders")
@RestController
class OrderController(private val orderService: OrderService) {

    @PostMapping
    fun createDeliveryInfo(@RequestBody orderRequest: OrderRequest): Order {
        return orderService.create(orderRequest)
    }

    @GetMapping("/{id}")
    fun getDeliveryInfo(@PathVariable id: Long): Order {
        return orderService.get(id)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    fun deleteDeliveryInfo(@PathVariable id: Long) {
        orderService.delete(id)
    }
}