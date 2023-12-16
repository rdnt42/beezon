package com.beezon.storage_manager.repository

import com.beezon.storage_manager.entity.Cell
import org.springframework.data.jpa.repository.JpaRepository

interface CellRepository : JpaRepository<Cell, Long>