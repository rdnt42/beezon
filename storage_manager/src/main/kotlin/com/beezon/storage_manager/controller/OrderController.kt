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
    fun create(@RequestBody orderRequest: OrderRequest): Order {
        return orderService.create(orderRequest)
    }

    @PostMapping("/get-or-create")
    fun getOrCreate(@RequestBody orderRequest: OrderRequest): Order {
        return orderService.getOrCreate(orderRequest)
    }

    @GetMapping("/{id}")
    fun get(@PathVariable id: String): Order {
        return orderService.get(id)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: String) {
        orderService.delete(id)
    }
}