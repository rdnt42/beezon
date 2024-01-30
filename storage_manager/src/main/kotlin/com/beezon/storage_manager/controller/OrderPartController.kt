package com.beezon.storage_manager.controller

import com.beezon.storage_manager.dto.request.OrderPartRequest
import com.beezon.storage_manager.entity.OrderPart
import com.beezon.storage_manager.service.OrderPartService
import org.springframework.web.bind.annotation.*

@RequestMapping("/api/v1/order-parts")
@RestController
class OrderPartController(private val orderPartService: OrderPartService) {

    @PostMapping
    fun create(@RequestBody request: OrderPartRequest): OrderPart {
        return orderPartService.create(request)
    }

}