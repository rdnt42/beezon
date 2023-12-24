package com.beezon.storage_manager.repository

import com.beezon.storage_manager.entity.OrderPart
import org.springframework.data.jpa.repository.JpaRepository

interface OrderPartRepository : JpaRepository<OrderPart, Long>