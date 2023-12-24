package com.beezon.storage_manager.dto.request

data class OrderRequest(
    val orderNum: String,

    val client: String,

    val itemsCount: Int,

    val cellId: Long?,
)
