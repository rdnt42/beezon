package com.beezon.storage_manager.dto.request

data class OrderRequest(
    val barCode: String,

    val client: String,

    val itemsCount: Int,

    val cellId: Long,
)
