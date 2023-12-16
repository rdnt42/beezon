package com.beezon.storage_manager.repository

import com.beezon.storage_manager.entity.Order
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository : JpaRepository<Order, Long>